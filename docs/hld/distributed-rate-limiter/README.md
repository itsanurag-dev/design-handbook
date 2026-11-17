# High-Level Design: Distributed Rate Limiting System

## 1. Problem Statement & Scope
A high-throughput, low-latency distributed rate limiter capable of enforcing API usage limits across millions of concurrent users and microservice endpoints, preventing DDoS attacks, brute-force requests, and downstream cascade failures.

* **In-Scope**: Tiered rate limits (per IP, per API Token, per Route), sliding window counter algorithm, Redis cluster coordination, graceful rate limiting response (`HTTP 429 Too Many Requests`).
* **Out-of-Scope**: WAF SQL injection/XSS payload inspection.

---

## 2. Requirements

### 2.1 Functional Requirements
* **FR-1 (Limit Enforcement)**: Enforce limits based on client identification (User ID, IP, or API Key).
* **FR-2 (Dynamic Rule Config)**: Update rate limit thresholds dynamically without restarting gateway servers.
* **FR-3 (HTTP Headers)**: Return standard rate-limit headers:
  * `X-RateLimit-Limit`: Maximum requests permitted in window.
  * `X-RateLimit-Remaining`: Tokens remaining.
  * `X-RateLimit-Reset`: Unix timestamp until window resets.
* **FR-4 (HTTP 429)**: Return `429 Too Many Requests` when limits are breached.

### 2.2 Non-Functional Requirements
* **Latency**: Overhead added to API requests must be $\le \mathbf{2 \text{ ms}}$ at p99.
* **Fault Tolerance & Fail-Open**: If the rate-limiting cluster fails, the system must **fail-open** to avoid blocking legitimate traffic.
* **Accuracy**: Distributed synchronization must avoid race conditions between concurrent requests.

---

## 3. High-Level Architecture Diagram

```mermaid
flowchart TD
    Client([HTTP Client / Mobile / Web])
    Edge[API Gateway / Envoy Proxy]
    Downstream[Downstream Backend Microservices]

    subgraph RateLimitingTier["Distributed Rate Limiting Infrastructure"]
        RLFilter[Rate Limiter Interceptor / Filter]
        LocalCache[("Local In-Memory Cache<br/>(Caffeine - 100ms TTL)")]
        RedisCluster[("Redis Cluster<br/>(Sliding Window Lua Scripts)")]
        ConfigStore[(Dynamic Config Store<br/>Consul / etcd)]
    end

    Client -->|1. HTTP Request| Edge
    Edge -->|2. Check Limit| RLFilter
    RLFilter -->|3. Check Local L1 Cache| LocalCache
    LocalCache -.->|L1 Miss| RedisCluster
    RLFilter -->|Run Atomic Lua Script| RedisCluster

    alt Under Limit
        RLFilter -->> Edge: Allow (Return Remaining Tokens)
        Edge -->|4. Forward Request| Downstream
    else Limit Exceeded
        RLFilter -->> Edge: Reject (Limit Reached)
        Edge -->> Client: HTTP 429 Too Many Requests
    end

    ConfigStore -.->|Sync Rules| RLFilter
```

---

## 4. Sliding Window Counter: Redis Lua Implementation
To guarantee atomic checking and incrementing across distributed nodes without race conditions, an atomic Redis Lua script is evaluated:

```lua
-- KEYS[1]: Rate limit key (e.g. rate:user:101)
-- ARGV[1]: Current timestamp (milliseconds)
-- ARGV[2]: Window size in milliseconds (e.g. 60000)
-- ARGV[3]: Maximum allowed requests in window

local key = KEYS[1]
local now = tonumber(ARGV[1])
local window = tonumber(ARGV[2])
local limit = tonumber(ARGV[3])
local clearBefore = now - window

-- 1. Remove requests older than the sliding window
redis.call('ZREMRANGEBYSCORE', key, 0, clearBefore)

-- 2. Count current elements in window
local currentRequests = redis.call('ZCARD', key)

if currentRequests < limit then
    -- 3. Add current request timestamp
    redis.call('ZADD', key, now, now)
    redis.call('PEXPIRE', key, window)
    return {1, limit - currentRequests - 1} -- Allowed, remaining
else
    return {0, 0} -- Blocked
end
```
