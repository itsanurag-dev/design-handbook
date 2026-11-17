# High-Level Design: Social Network News Feed (Instagram / Twitter)

## 1. Problem Statement & Scope
A massive-scale news feed generation and fan-out system where users can publish posts (images, text) and view a personalized chronological/ranked timeline of posts from users they follow.

* **In-Scope**: Post publishing, timeline generation (feed generation), fan-out write vs load (Push vs Pull), pagination, media metadata storage.
* **Out-of-Scope**: Machine-learning ranking models, live video broadcasting.

---

## 2. Requirements

### 2.1 Functional Requirements
* **FR-1 (Publish Post)**: Users can upload posts with image URLs and captions.
* **FR-2 (News Feed Generation)**: Users can retrieve a chronological feed composed of recent posts from followed users.
* **FR-3 (Follow Graph)**: Users can follow/unfollow other users.
* **FR-4 (Pagination)**: Cursor-based pagination (`limit=20`, `cursor=<timestamp>`).

### 2.2 Non-Functional Requirements
* **Latency**: Fetching feed p99 latency < **200 ms**.
* **Availability**: 99.99% read availability.
* **Scale**: Support 500M DAU with 100:1 read-to-write ratio.

---

## 3. High-Level Architecture Diagram

```mermaid
flowchart TD
    User([Mobile / Web Client])
    CDN[Edge CDN]
    LB[API Gateway / Load Balancer]
    
    subgraph Services["Application Services"]
        PostService[Post Service]
        FeedService[Timeline / Feed Service]
        FanoutWorker[Async Fan-out Worker Pool]
        FollowService[Social Graph Service]
    end
    
    subgraph Caches["Cache Layer"]
        FeedCache[("Redis Feed Cache<br/>(User Timelines - Top 800 Posts)")]
    end
    
    subgraph DataTier["Databases"]
        GraphDB[(Graph Store / Neo4j / MySQL<br/>Follower Relationships)]
        PostDB[(Cassandra / DynamoDB<br/>Post Data & Media References)]
        MQ{{Apache Kafka<br/>'post-published' Topic}}
    end

    %% Read Path
    User -->|1. GET /feed| LB
    LB --> FeedService
    FeedService -->|2. Fetch Timeline| FeedCache
    FeedCache -.->|Cache Miss| PostDB
    FeedService -->> User: Return 20 Posts

    %% Write Path
    User -->|3. POST /posts| LB
    LB --> PostService
    PostService -->|4. Save Post| PostDB
    PostService -->|5. Publish Event| MQ
    
    %% Fan-out Path
    MQ --> FanoutWorker
    FanoutWorker -->|6. Get Followers| GraphDB
    FanoutWorker -->|7. Pre-compute Feed| FeedCache
```

---

## 4. Fan-Out Deep-Dive: Hybrid Push vs. Pull Strategy
A pure **Push (Fan-out-on-write)** model breaks when celebrities with 50M followers post (writing to 50M Redis lists causes catastrophic write amplification). A pure **Pull (Fan-out-on-load)** model causes high read latency.

**The Hybrid Production Solution**:
1. **Regular Users (< 25,000 followers)**: Use **Fan-out on Write (Push)**. When user posts, workers write post IDs into all followers' Redis timeline lists.
2. **Celebrity Users (> 25,000 followers)**: Use **Fan-out on Load (Pull)**. Posts are NOT pushed to followers. Instead, when a follower requests their feed, the system fetches their pre-computed regular feed and merges it at read-time with recent posts from the celebrities they follow.

