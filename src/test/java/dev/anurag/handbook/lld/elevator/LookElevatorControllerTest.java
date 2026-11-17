package dev.anurag.handbook.lld.elevator;

import dev.anurag.handbook.lld.elevator.model.*;
import dev.anurag.handbook.lld.elevator.service.LookElevatorController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LOOK Elevator Controller Invariant Tests")
class LookElevatorControllerTest {

    private LookElevatorController controller;

    @BeforeEach
    void setUp() {
        controller = new LookElevatorController(0, 10, 0);
    }

    @Test
    @DisplayName("Should initialize at ground floor in IDLE state")
    void testInitialState() {
        assertEquals(0, controller.getCurrentFloor());
        assertEquals(ElevatorState.IDLE, controller.getCurrentState());
        assertEquals(Direction.NONE, controller.getCurrentDirection());
    }

    @Test
    @DisplayName("Should sequentially travel upward to highest requested floor using LOOK algorithm")
    void testAscendingTrajectory() throws InterruptedException {
        controller.submitRequest(new InternalRequest(3));
        controller.submitRequest(new ExternalRequest(5, Direction.UP));

        while (controller.getCurrentFloor() < 3) {
            controller.step();
        }
        assertEquals(3, controller.getCurrentFloor());

        while (controller.getCurrentFloor() < 5) {
            controller.step();
        }
        assertEquals(5, controller.getCurrentFloor());
        controller.step();

        assertTrue(controller.getUpStops().isEmpty());
    }

    @Test
    @DisplayName("Should reject invalid floors beyond system boundaries")
    void testBoundaryValidation() {
        assertThrows(IllegalArgumentException.class, () -> controller.submitRequest(new InternalRequest(-1)));
        assertThrows(IllegalArgumentException.class, () -> controller.submitRequest(new InternalRequest(11)));
    }

    @Test
    @DisplayName("Concurrency: 20 concurrent threads submitting requests must never corrupt state")
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testConcurrentSubmissions() throws InterruptedException {
        int threads = 20;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            final int targetFloor = (i % 10) + 1;
            executor.submit(() -> {
                try {
                    controller.submitRequest(new InternalRequest(targetFloor));
                } finally {
                    latch.countDown();
                }
            });
        }

        assertTrue(latch.await(3, TimeUnit.SECONDS));
        executor.shutdown();

        int totalEnqueued = controller.getUpStops().size() + controller.getDownStops().size();
        assertEquals(10, totalEnqueued, "All unique concurrent floor submissions must be recorded");
    }
}
