package dev.anurag.handbook.lld.pubsub;

import dev.anurag.handbook.lld.pubsub.broker.PubSubBroker;
import dev.anurag.handbook.lld.pubsub.model.Message;
import dev.anurag.handbook.lld.pubsub.subscriber.Subscriber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("In-Memory Pub-Sub Broker Invariant Tests")
class PubSubBrokerTest {

    private PubSubBroker<String> broker;
    private ExecutorService testPool;

    static class TestSubscriber implements Subscriber<String> {
        private final String id;
        private final List<Message<String>> received = new CopyOnWriteArrayList<>();
        private final CountDownLatch latch;

        TestSubscriber(String id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public String getId() { return id; }

        @Override
        public void onMessage(Message<String> message) {
            received.add(message);
            if (latch != null) {
                latch.countDown();
            }
        }

        public List<Message<String>> getReceived() { return received; }
    }

    @BeforeEach
    void setUp() {
        testPool = Executors.newFixedThreadPool(4);
        broker = new PubSubBroker<>(testPool);
    }

    @AfterEach
    void tearDown() {
        broker.shutdown();
        testPool.shutdownNow();
    }

    @Test
    @DisplayName("Should deliver published message to all registered subscribers on topic")
    void testMessageDelivery() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        TestSubscriber sub1 = new TestSubscriber("sub-1", latch);
        TestSubscriber sub2 = new TestSubscriber("sub-2", latch);

        broker.subscribe("orders", sub1);
        broker.subscribe("orders", sub2);

        broker.publish("orders", "order:created:1001");

        assertTrue(latch.await(3, TimeUnit.SECONDS));
        assertEquals(1, sub1.getReceived().size());
        assertEquals("order:created:1001", sub1.getReceived().get(0).getPayload());
        assertEquals("order:created:1001", sub2.getReceived().get(0).getPayload());
    }

    @Test
    @DisplayName("Should stop delivering to subscriber after unsubscribe")
    void testUnsubscribe() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        TestSubscriber sub1 = new TestSubscriber("sub-1", latch);
        TestSubscriber sub2 = new TestSubscriber("sub-2", null);

        broker.subscribe("news", sub1);
        broker.subscribe("news", sub2);
        broker.unsubscribe("news", sub2);

        broker.publish("news", "breaking:news:v1");

        assertTrue(latch.await(3, TimeUnit.SECONDS));
        assertEquals(1, sub1.getReceived().size());
        assertEquals(0, sub2.getReceived().size());
    }
}
