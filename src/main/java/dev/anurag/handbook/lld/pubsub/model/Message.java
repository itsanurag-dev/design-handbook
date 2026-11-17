package dev.anurag.handbook.lld.pubsub.model;

import java.time.Instant;
import java.util.UUID;

public class Message<T> {
    private final String id;
    private final String topic;
    private final T payload;
    private final Instant timestamp;

    public Message(String topic, T payload) {
        this.id = UUID.randomUUID().toString();
        this.topic = topic;
        this.payload = payload;
        this.timestamp = Instant.now();
    }

    public String getId() { return id; }
    public String getTopic() { return topic; }
    public T getPayload() { return payload; }
    public Instant getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "Message{id='" + id + "', topic='" + topic + "', payload=" + payload + "}";
    }
}

