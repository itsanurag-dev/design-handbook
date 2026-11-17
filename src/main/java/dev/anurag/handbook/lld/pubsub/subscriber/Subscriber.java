package dev.anurag.handbook.lld.pubsub.subscriber;

import dev.anurag.handbook.lld.pubsub.model.Message;

public interface Subscriber<T> {
    String getId();
    void onMessage(Message<T> message);
}

