<div align="center">

# Software Architecture & System Design Handbook

[![Java 11+](https://img.shields.io/badge/Java-11%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen?style=for-the-badge&logo=githubactions&logoColor=white)](#)
[![JUnit 5](https://img.shields.io/badge/Tested%20With-JUnit%205-25A162?style=for-the-badge&logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](LICENSE)

An opinionated, production-grade engineering handbook for software architecture: 
**23 Gang of Four (GoF) Design Patterns**, thread-safe **Low-Level Designs (LLD)**, 
and scalable **High-Level Distributed System Designs (HLD)** with Mermaid architecture diagrams.

[Explore Patterns](#pillar-1-oops--gof-design-patterns) • 
[Explore LLD](#pillar-2-low-level-system-design-lld) • 
[Explore HLD](#pillar-3-high-level-system-design-hld) • 
[Architecture Notes](#architecture-fundamentals--adrs)

</div>

---

## Table of Contents
1. [Pillar 1: OOPS & GoF Design Patterns](#pillar-1-oops--gof-design-patterns)
2. [Pillar 2: Low-Level System Design (LLD)](#pillar-2-low-level-system-design-lld)
3. [Pillar 3: High-Level System Design (HLD)](#pillar-3-high-level-system-design-hld)
4. [Architecture Fundamentals & ADRs](#architecture-fundamentals--adrs)
5. [Getting Started & Build Instructions](#getting-started)
6. [Engineering Standards](#engineering-standards)

---

## Pillar 1: OOPS & GoF Design Patterns

Every pattern is implemented with clean domain modeling, thread safety where applicable, and automated JUnit 5 tests.

### Creational Patterns (5)
* [x] **[Factory Method](src/main/java/dev/anurag/handbook/patterns/creational/factory)**: Interface-driven object instantiation.
* [x] **[Abstract Factory](src/main/java/dev/anurag/handbook/patterns/creational/abstractfactory)**: Families of related or dependent objects.
* [x] **[Builder](src/main/java/dev/anurag/handbook/patterns/creational/builder)**: Fluent step-by-step construction of complex representations.
* [x] **[Prototype](src/main/java/dev/anurag/handbook/patterns/creational/prototype)**: Cloning existing objects without coupling to concrete classes.
* [x] **[Singleton](src/main/java/dev/anurag/handbook/patterns/creational/singleton)**: Thread-safe, reflection-proof, and serialization-safe singletons (Bill Pugh, Double-Checked Locking, Enum).

### Structural Patterns (7)
* [x] **[Adapter](src/main/java/dev/anurag/handbook/patterns/structural/adapter)**: Bridges incompatible interfaces via object composition.
* [x] **[Bridge](src/main/java/dev/anurag/handbook/patterns/structural/bridge)**: Decouples abstraction hierarchies from implementations.
* [x] **[Composite](src/main/java/dev/anurag/handbook/patterns/structural/composite)**: Tree structures treating leaf and branch nodes uniformly.
* [x] **[Decorator](src/main/java/dev/anurag/handbook/patterns/structural/decorator)**: Dynamically attaches responsibilities without subclass explosion.
* [x] **[Facade](src/main/java/dev/anurag/handbook/patterns/structural/facade)**: Simplified higher-level entrypoint to complex subsystems.
* [x] **[Flyweight](src/main/java/dev/anurag/handbook/patterns/structural/flyweight)**: Massively reduces memory consumption via shared intrinsic state.
* [x] **[Proxy](src/main/java/dev/anurag/handbook/patterns/structural/proxy)**: Virtual, protection, and logging proxies controlling object access.

### Behavioral Patterns (11)
* [x] **[Chain of Responsibility](src/main/java/dev/anurag/handbook/patterns/behavioral/chainofresponsibility)**: Sequential request handling with dynamic handler chains.
* [x] **[Command](src/main/java/dev/anurag/handbook/patterns/behavioral/command)**: Encapsulates actions as standalone objects supporting undo/redo.
* [x] **[Interpreter](src/main/java/dev/anurag/handbook/patterns/behavioral/interpreter)**: Defines grammatical representations and abstract syntax evaluators.
* [x] **[Iterator](src/main/java/dev/anurag/handbook/patterns/behavioral/iterator)**: Sequential traversal without exposing internal structures.
* [x] **[Mediator](src/main/java/dev/anurag/handbook/patterns/behavioral/mediator)**: Centralized coordination eliminating many-to-many dependencies.
* [x] **[Memento](src/main/java/dev/anurag/handbook/patterns/behavioral/memento)**: Captures internal object state without violating encapsulation.
* [x] **[Observer](src/main/java/dev/anurag/handbook/patterns/behavioral/observer)**: Thread-safe event publishing (`CopyOnWriteArrayList`).
* [x] **[State](src/main/java/dev/anurag/handbook/patterns/behavioral/state)**: Finite State Machines altering behaviors upon state transition.
* [x] **[Strategy](src/main/java/dev/anurag/handbook/patterns/behavioral/strategy)**: Family of interchangeable algorithms selected at runtime.
* [x] **[Template Method](src/main/java/dev/anurag/handbook/patterns/behavioral/templatemethod)**: Invariant skeleton defining abstract algorithmic steps.
* [x] **[Visitor](src/main/java/dev/anurag/handbook/patterns/behavioral/visitor)**: Double-dispatch adding new operations without modifying entities.

---

## Pillar 2: Low-Level System Design (LLD)

Every problem contains a formal problem statement, class diagram (Mermaid), domain entities, and multi-threaded test suites.

| System Problem | Patterns Used | Concurrency Primitives | Status |
| :--- | :--- | :--- | :---: |
| **[Elevator System](src/main/java/dev/anurag/handbook/lld/elevator)** | State, Strategy, Command | `ReentrantLock`, `Condition` | Complete |
| **[Parking Lot](src/main/java/dev/anurag/handbook/lld/parkingslot)** | Strategy, Factory | Concurrent Hash Maps, Atomic Booleans | Complete |
| **[Thread-Safe LRU Cache](src/main/java/dev/anurag/handbook/concurrency/cache)** | Doubly-Linked List + Hash Map | `ReentrantReadWriteLock` | Complete |
| **[Token-Bucket Rate Limiter](src/main/java/dev/anurag/handbook/concurrency/ratelimiter)** | Leaky / Token Bucket | Atomic Refill, CAS Operations | Complete |
| **[Distributed Snowflake Generator](src/main/java/dev/anurag/handbook/concurrency/idgenerator)**| Sequence Generator | Synchronized Bit Manipulation | Complete |
| **[Consistent Hashing Router](src/main/java/dev/anurag/handbook/concurrency/consistenthash)** | Virtual Nodes Ring | MD5 32-bit Hash, ReadWriteLock | Complete |
| **[In-Memory File System](src/main/java/dev/anurag/handbook/lld/filesystem)** | Composite | Hierarchical INode Traversal | Complete |
| **[Typeahead Autocomplete](src/main/java/dev/anurag/handbook/lld/autocomplete)** | Trie + Min-Heap | Prefix Matching | Complete |
| **[Tic-Tac-Toe Game](src/main/java/dev/anurag/handbook/lld/tictactoe)** | State | $O(1)$ Array Verification | Complete |
| **[Excel Formula Engine](src/main/java/dev/anurag/handbook/lld/excel)** | DAG / Topological Sort | Formula Evaluation | Complete |

---

## Pillar 3: High-Level System Design (HLD)

Comprehensive 11-step distributed design blueprints including capacity estimations, storage selection, and Mermaid architecture diagrams.

* [x] **[Distributed URL Shortener (TinyURL)](docs/hld/url-shortener/README.md)**: Token range leasing (KGS), Base62, Redis caching, Cassandra key-value store.
* [x] **[Chat & Real-Time Messaging (WhatsApp)](docs/hld/chat-messenger-whatsapp/README.md)**: WebSockets, Presence Service, Message Queue fan-out, Cassandra.
* [x] **[Cloud File Storage & Sync (Dropbox)](docs/hld/cloud-storage-dropbox/README.md)**: Content-defined chunking, deduplication, Merkle tree sync.
* [x] **[Distributed Rate Limiter](docs/hld/distributed-rate-limiter/README.md)**: Sliding window counter, Redis cluster, Token Bucket.
* [x] **[Social Network News Feed (Instagram/Twitter)](docs/hld/social-network-instagram/README.md)**: Fan-out-on-write vs on-load, feed ranking.

---

## Architecture Fundamentals & ADRs

- **[ADR 0001: Standardize Testing Framework on JUnit 5 Jupiter](docs/adr/0001-use-junit-5-jupiter.md)**
- **[ADR 0002: Package-by-Feature for Low-Level Designs](docs/adr/0002-package-by-feature-in-lld.md)**
- **[ADR 0003: Text-Based Mermaid for Architectural Diagrams](docs/adr/0003-mermaid-diagram-standard.md)**

---

## Getting Started

### Prerequisites
* JDK 11 or higher (configured with `--release 11`)
* Apache Maven 3.8+

### Build & Run Tests
```bash
# Clone the repository
git clone https://github.com/itsanurag-dev/design-handbook.git
cd design-handbook

# Run all automated JUnit 5 tests
mvn clean test

# Generate JaCoCo code coverage report
mvn test jacoco:report
```

---

## License
Distributed under the MIT License. See [LICENSE](LICENSE) for details.
