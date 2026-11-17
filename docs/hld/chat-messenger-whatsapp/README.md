# High-Level Design: Chat & Real-Time Messaging System (WhatsApp / Slack)

## 1. Problem Statement & Scope
A globally distributed, ultra-low latency real-time messaging architecture supporting 1:1 direct messaging, multi-user group chats, user presence indication (online/last seen), and delivery receipts (Sent, Delivered, Read).

* **In-Scope**: Real-time duplex message exchange, offline queueing and synchronization, presence state, end-to-end encryption transport, group fan-out.
* **Out-of-Scope**: Voice/video streaming (WebRTC media relay), end-to-end media transcoding.

---

## 2. Requirements

### 2.1 Functional Requirements
* **FR-1 (1:1 Messaging)**: Low-latency two-way message exchange between two users.
* **FR-2 (Delivery Receipts)**: State acknowledgments: Sent (server received), Delivered (peer device received), Read (peer opened).
* **FR-3 (Offline Queueing)**: Buffer messages when recipient device is offline; deliver upon reconnect.
* **FR-4 (Group Chat)**: Multi-party messaging up to 1,000 members per group.
* **FR-5 (Presence Engine)**: Real-time broadcast of Online / Offline / Typing status with heartbeat keepalives.

### 2.2 Non-Functional Requirements
* **Latency**: p99 message delivery latency < **100 ms** for online users.
* **Availability**: 99.99% service availability.
* **Durability & Ordering**: Strict FIFO ordering per conversation thread; zero message loss.
* **Scale**: Support 500M Daily Active Users (DAU) and 25M concurrent persistent socket connections.

---

## 3. Capacity Estimation & Math
* **Messages per day**: 500M users $\times$ 40 messages/day $\approx$ **20 Billion messages/day**.
* **Average Message QPS**:
  $$\text{Average QPS} = \frac{20,000,000,000}{86,400} \approx 231,000 \text{ msgs/sec}$$
  $$\text{Peak QPS (2.5x)} \approx \mathbf{580,000 \text{ msgs/sec}}$$
* **Storage per day**: $20\text{B} \times 100 \text{ bytes (text + metadata)} \approx \mathbf{2 \text{ TB / day}}$ ($\approx 730 \text{ TB / year}$).
* **Active WebSocket Memory**: 25M concurrent connections $\times$ 10 KB OS socket buffer $\approx \mathbf{250 \text{ GB RAM}}$ across gateway clusters.

---

## 4. API Design & Protocols
* **Transport**: **WebSockets** (persistent bidirectional TCP channel) with **gRPC/Protocol Buffers** payload serialization for minimal wire overhead.
* **HTTP Fallback Endpoints**:
  * `POST /api/v1/messages`: Fallback ingestion when WebSocket fails.
  * `GET /api/v1/conversations/{id}/sync?since={seq}`: Delta synchronization of missed messages on client reconnection.

---

## 5. Data Model & Database Architecture

```sql
-- Conversations Table
CREATE TABLE conversation (
    conversation_id VARCHAR(36) PRIMARY KEY,
    is_group BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Message History Table (Partitioned by Conversation ID)
CREATE TABLE message (
    conversation_id VARCHAR(36),
    sequence_id BIGINT,
    message_id VARCHAR(36),
    sender_id VARCHAR(36) NOT NULL,
    payload TEXT NOT NULL,
    status VARCHAR(16) NOT NULL, -- SENT, DELIVERED, READ
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY (conversation_id, sequence_id)
);
```

* **Storage Engine Choice**: **Apache Cassandra (ScyllaDB)**.
  * *Justification*: Write-heavy workload (LSM-tree storage). Partitioning by `conversation_id` with `sequence_id` as clustering key guarantees sequential, range-query friendly retrieval with linear horizontal write scaling.

---

## 6. High-Level Architecture Diagram

```mermaid
flowchart TD
    ClientA([Client A - Sender])
    ClientB([Client B - Recipient])
    
    LB[Global TCP Load Balancer]
    
    subgraph GatewayCluster["Edge WebSocket Gateway Layer"]
        WS1[WebSocket Gateway 1]
        WS2[WebSocket Gateway 2]
    end
    
    subgraph RoutingCore["Messaging Engine"]
        SessionRegistry[("Redis Cluster<br/>(User-to-Server Session Map)")]
        MQ{{Apache Kafka / RabbitMQ<br/>Partitioned Message Bus}}
        MessageService[Message Routing Service]
        PresenceService[Presence & Heartbeat Service]
    end
    
    subgraph PersistentStorage["Data Tier"]
        Cassandra[(Cassandra Message Store<br/>Partition: conversation_id)]
    end

    ClientA -->|1. WebSocket Send| WS1
    WS1 -->|2. Route Message| MessageService
    MessageService -->|3. Persist Message| Cassandra
    MessageService -->|4. Lookup Recipient Gateway| SessionRegistry
    SessionRegistry -.->|Recipient is on WS2| MessageService
    MessageService -->|5. Forward via MQ| MQ
    MQ -->|6. Deliver Event| WS2
    WS2 -->|7. Push WebSocket| ClientB

    ClientA -.->|Heartbeat Keepalive| PresenceService
    PresenceService --> SessionRegistry
```

---

## 7. Deep-Dive Workflows

### 1:1 Message Routing Sequence

```mermaid
sequenceDiagram
    autonumber
    actor A as User A
    participant WS_A as Gateway A
    participant Svc as Message Service
    participant Redis as Session Registry
    participant DB as Cassandra
    participant WS_B as Gateway B
    actor B as User B

    A->>WS_A: WS send {to: B, text: "Hello", clientMsgId: "c1"}
    WS_A->>Svc: Process Message
    Svc->>DB: INSERT message (conversationId, seqId, text, status='SENT')
    Svc-->>WS_A: ACK {clientMsgId: "c1", status: "SENT", seqId: 401}
    WS_A-->>A: Double-Tick (Sent)
    
    Svc->>Redis: GET session:user:B
    alt User B Online (Connected to Gateway B)
        Redis-->>Svc: gateway_id="WS_B"
        Svc->>WS_B: Dispatch {to: B, msgId: "m1", text: "Hello"}
        WS_B->>B: Push WS message
        B-->>WS_B: ACK {msgId: "m1", status: "DELIVERED"}
        WS_B->>Svc: Update status='DELIVERED'
        Svc->>DB: UPDATE status='DELIVERED'
        Svc->>WS_A: Push status update
        WS_A-->>A: Double-Tick Grey (Delivered)
    else User B Offline
        Redis-->>Svc: nil (Offline)
        Svc->>DB: Queue in offline_inbox
    end
```
