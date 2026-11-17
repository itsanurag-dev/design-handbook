package dev.anurag.handbook.concurrency;

import dev.anurag.handbook.concurrency.cache.LRUCache;
import dev.anurag.handbook.concurrency.consistenthash.ConsistentHashRouter;
import dev.anurag.handbook.concurrency.idgenerator.SnowflakeIdGenerator;
import dev.anurag.handbook.concurrency.ratelimiter.TokenBucketRateLimiter;
import dev.anurag.handbook.lld.parkingslot.model.*;
import dev.anurag.handbook.lld.parkingslot.service.ParkingLotService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("P0 Concurrency & LLD Core Invariant Tests")
class CorePrimitivesTest {

    @Test
    @DisplayName("LRUCache: should evict least recently accessed entry upon reaching capacity")
    void testLRUEviction() {
        LRUCache<Integer, String> cache = new LRUCache<>(2);
        cache.put(1, "one");
        cache.put(2, "two");
        assertEquals("one", cache.get(1).orElse(null));

        cache.put(3, "three");

        assertEquals("one", cache.get(1).orElse(null));
        assertTrue(cache.get(2).isEmpty(), "Key 2 should have been evicted");
        assertEquals("three", cache.get(3).orElse(null));
    }

    @Test
    @DisplayName("TokenBucketRateLimiter: should reject requests once burst capacity is exhausted")
    void testRateLimiterBurst() {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(2, 10.0);
        assertTrue(limiter.tryAcquire());
        assertTrue(limiter.tryAcquire());
        assertFalse(limiter.tryAcquire(), "Bucket should be exhausted after 2 immediate tokens");
    }

    @Test
    @DisplayName("SnowflakeIdGenerator: concurrent threads must generate strictly unique IDs")
    void testSnowflakeUniqueness() throws InterruptedException {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1);
        int totalRequests = 10_000;
        Set<Long> generatedIds = ConcurrentHashMap.newKeySet();
        ExecutorService executor = Executors.newFixedThreadPool(8);
        CountDownLatch latch = new CountDownLatch(totalRequests);

        for (int i = 0; i < totalRequests; i++) {
            executor.submit(() -> {
                try {
                    generatedIds.add(generator.nextId());
                } finally {
                    latch.countDown();
                }
            });
        }

        assertTrue(latch.await(5, TimeUnit.SECONDS));
        executor.shutdown();
        assertEquals(totalRequests, generatedIds.size(), "Every generated Snowflake ID must be unique");
    }

    @Test
    @DisplayName("ConsistentHashRouter: route should balance keys and remain deterministic")
    void testConsistentHashing() {
        List<String> nodes = List.of("node-1", "node-2", "node-3");
        ConsistentHashRouter<String> router = new ConsistentHashRouter<>(100, nodes);

        Optional<String> target1 = router.route("user:1001");
        Optional<String> target2 = router.route("user:1001");

        assertTrue(target1.isPresent());
        assertEquals(target1.get(), target2.get(), "Routing for the same key must be deterministic");
    }

    @Test
    @DisplayName("ParkingLotService: should concurrently park vehicles without double-booking spots")
    void testParkingLotConcurrency() throws InterruptedException {
        List<ParkingSpot> spots = List.of(
            new ParkingSpot(1, 101, VehicleType.COMPACT),
            new ParkingSpot(1, 102, VehicleType.COMPACT)
        );
        ParkingLotService service = new ParkingLotService(spots);

        int threads = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Optional<Ticket>> results = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch latch = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            final String plate = "MH-01-" + i;
            executor.submit(() -> {
                try {
                    results.add(service.parkVehicle(plate, VehicleType.COMPACT));
                } finally {
                    latch.countDown();
                }
            });
        }

        assertTrue(latch.await(3, TimeUnit.SECONDS));
        executor.shutdown();

        long successfulParkings = results.stream().filter(Optional::isPresent).count();
        assertEquals(2, successfulParkings, "Only exactly 2 vehicles can park in a 2-spot facility");
    }
}

