package dev.anurag.handbook.lld.pubsub.broker;

import dev.anurag.handbook.lld.pubsub.model.Message;
import dev.anurag.handbook.lld.pubsub.subscriber.Subscriber;

import java.util.List;
import java.util.concurrent.*;

/**
 * Thread-safe, asynchronous in-memory Pub-Sub Message Broker.
 * Supports dynamic topic subscriptions and isolated consumer worker thread pools.
 */
public class PubSubBroker<T> {

    private final ConcurrentMap<String, CopyOnWriteArrayList<Subscriber<T>>> topicSubscribers = new ConcurrentHashMap<>();
    private final ExecutorService dispatcherPool;

    public PubSubBroker() {
        this(Executors.newCachedThreadPool());
    }

    public PubSubBroker(ExecutorService dispatcherPool) {
        this.dispatcherPool = dispatcherPool;
    }

    public void subscribe(String topic, Subscriber<T> subscriber) {
        topicSubscribers.computeIfAbsent(topic, k -> new CopyOnWriteArrayList<>()).add(subscriber);
    }

    public void unsubscribe(String topic, Subscriber<T> subscriber) {
        List<Subscriber<T>> subscribers = topicSubscribers.get(topic);
        if (subscribers != null) {
            subscribers.remove(subscriber);
        }
    }

    public void publish(String topic, T payload) {
        Message<T> message = new Message<>(topic, payload);
        List<Subscriber<T>> subscribers = topicSubscribers.get(topic);

        if (subscribers != null && !subscribers.isEmpty()) {
            for (Subscriber<T> subscriber : subscribers) {
                dispatcherPool.submit(() -> {
                    try {
                        subscriber.onMessage(message);
                    } catch (Exception e) {
                        System.err.println("Error dispatching message to subscriber " + subscriber.getId() + ": " + e.getMessage());
                    }
                });
            }
        }
    }

    public int getSubscriberCount(String topic) {
        List<Subscriber<T>> subscribers = topicSubscribers.get(topic);
        return subscribers == null ? 0 : subscribers.size();
    }

    public void shutdown() {
        dispatcherPool.shutdown();
    }
}
