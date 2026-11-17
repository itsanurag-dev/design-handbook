# High-Level Design: Smart Parking Lot Facility (IoT Multi-Gate Architecture)

## 1. Problem Statement & Scope
An enterprise multi-level, automated smart parking lot management system. Manages real-time vehicle entry/exit gates, automated license plate recognition (ALPR), dynamic spot assignment, real-time signage boards, and contactless payments.

* **In-Scope**: Multi-gate concurrent entry/exit, spot reservation & status tracking, dynamic fee calculation, IoT barrier control, digital availability displays.
* **Out-of-Scope**: Physical hardware circuit firmware, payment gateway bank clearing settlement.

---

## 2. Requirements

### 2.1 Functional Requirements
* **FR-1 (Vehicle Entry)**: Scan vehicle at entry gate, verify space availability, assign closest spot by vehicle type (Motorcycle, Compact, Large), issue ticket, and open barrier.
* **FR-2 (Vehicle Exit)**: Scan ticket/plate at exit gate, compute parking duration fee, process payment, and lift barrier.
* **FR-3 (Real-Time Availability Displays)**: Update electronic floor display boards immediately when spots change occupancy.
* **FR-4 (Spot Allocation Strategy)**: Nearest-to-entry/exit, EV charging preference, and ADA accessibility prioritization.

### 2.2 Non-Functional Requirements
* **Latency**: Gate barrier open decision must take $\le \mathbf{500 \text{ ms}}$.
* **High Availability**: Gates must operate locally even during internet/cloud network partition (Offline Edge Gateway mode).
* **Concurrency**: Prevent double-booking of a single parking spot across concurrent entry gates.

---

## 3. High-Level Architecture Diagram

```mermaid
flowchart TD
    GateSensor[Entry / Exit Gate Camera & RFID]
    DisplaySign[Floor Digital Signage Boards]

    subgraph EdgeDevice["Local Edge Gate Controller"]
        EdgeBroker[Local MQTT / Edge Gateway]
        LocalDB[(SQLite Local Cache)]
    end

    subgraph CloudBackend["Central Parking Cloud Services"]
        Ingress[API Gateway / Load Balancer]
        AllocationService[Spot Allocation Service]
        BillingService[Billing & Payment Service]
        TelemetryService[IoT Telemetry Service]
        RedisCluster[("Redis Cluster<br/>(Real-Time Spot States)")]
        DB[(PostgreSQL Primary DB<br/>Floors, Spots, History)]
    end

    GateSensor -->|Plate / RFID Event| EdgeBroker
    EdgeBroker -->|Sync Gate Event| Ingress
    Ingress --> AllocationService
    AllocationService -->|Atomic Spot Claim| RedisCluster
    AllocationService -->|Persist Ticket| DB
    AllocationService -->> EdgeBroker: Open Barrier Command

    AllocationService --> TelemetryService
    TelemetryService -->|MQTT Broadcast| DisplaySign
```

---

## 4. Concurrent Spot Allocation & Double-Booking Prevention
To eliminate race conditions between multiple entry gates competing for the last remaining spot:
1. All available spots per vehicle type are stored in **Redis Sets** (`available_spots:compact`).
2. Allocation utilizes atomic `SPOP available_spots:compact` or a distributed lock (`Redlock`).
3. If successful, the spot ID is assigned and marked in PostgreSQL; if empty, the entry gate immediately displays "LOT FULL".

