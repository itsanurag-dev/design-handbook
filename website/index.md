---
layout: home

hero:
  name: "Software Architecture & System Design"
  text: "Production Reference Handbook"
  tagline: "An opinionated engineering handbook: 23 GoF Design Patterns, thread-safe Low-Level Designs (LLD), and scalable High-Level Designs (HLD) in Java."
  actions:
    - theme: brand
      text: "Explore HLD Blueprints"
      link: /hld/url-shortener
    - theme: alt
      text: "Concurrency Primitives"
      link: /concurrency/cache/lrucache
    - theme: alt
      text: "GoF Patterns Reference"
      link: /patterns-overview

features:
  - title: "23 Gang of Four Patterns"
    details: "Creational, structural, and behavioral design patterns in clean, idiomatic Java with realistic domains and thread-safety."
  - title: "Thread-Safe Concurrency"
    details: "Production-tested concurrency primitives: $O(1)$ LRU Cache, Token-Bucket Rate Limiter, Snowflake 64-bit ID Generator, and Consistent Hashing."
  - title: "High-Level Distributed Systems"
    details: "Complete 11-step architectural specifications with back-of-the-envelope math, failure analysis, and GitHub-native Mermaid diagrams."
  - title: "Low-Level Designs (LLD)"
    details: "End-to-end object-oriented designs: LOOK Elevator Controller, Multi-Floor Parking Lot, Pub-Sub Broker, and In-Memory File System."
  - title: "Modern JUnit 5 Test Suite"
    details: "Zero toy demos without tests. Automated multi-threaded race condition tests, AssertJ assertions, and JaCoCo coverage."
  - title: "Architecture Decision Records"
    details: "Documented technical choices, trade-offs, and invariants recorded in lightweight, immutable Markdown ADRs."
---

<div class="engineering-showcase">

## Engineering Domains at a Glance

| Pillar | Domain | Core Highlights | Production Ready? |
| :--- | :--- | :--- | :---: |
| **HLD** | **Distributed URL Shortener** | KGS Token leasing, Base62 encoding, Redis caching, Cassandra storage | Yes |
| **HLD** | **Real-Time Messaging (WhatsApp)** | WebSockets, Session Registry, Presence heartbeats, Kafka fan-out | Yes |
| **HLD** | **Cloud Storage (Dropbox)** | 4MB block chunking, SHA-256 deduplication, delta synchronization | Yes |
| **HLD** | **Distributed Rate Limiter** | Atomic sliding window counter, Redis Lua scripts, L1 Caffeine cache | Yes |
| **HLD** | **Social Network (Instagram)** | Hybrid push-pull fan-out, timeline caches, cursor pagination | Yes |
| **HLD** | **Smart Parking Facility** | IoT Edge gateways, atomic Redis spot sets, barrier controls | Yes |
| **LLD** | **SCAN/LOOK Elevator System** | `ReentrantLock` + `Condition` variables, zero busy-wait, starvation prevention | Yes |
| **LLD** | **Multi-Floor Parking Lot** | Atomic spot claim (`AtomicBoolean`), vehicle types, ticket billing | Yes |
| **LLD** | **Pub-Sub Message Broker** | Topic subscription registry, isolated worker thread pools | Yes |
| **Concurrency** | **$O(1)$ LRU Cache** | Doubly-linked list + HashMap, `ReentrantReadWriteLock` | Yes |
| **Concurrency** | **Token-Bucket Rate Limiter** | Lazy nanosecond refill, zero background threads | Yes |
| **Concurrency** | **Twitter Snowflake Generator** | 64-bit time-ordered IDs, node leases, clock rollback handling | Yes |

</div>

