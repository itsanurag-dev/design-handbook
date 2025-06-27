package com.codex.system.design.whatsapp;

import java.util.Queue;

/**
 * Represents a messenger service in WhatsApp.
 * Handles sending messages to users and groups.
 */
public class Messenger {
    /**
     * Sends a message from sender to receiver.
     */
    public void sendMsg(String msg, Sender sender, Receiver receiver) {
        pushMsgToUserQueue(sender, true, msg);
        pushMsgToUserQueue(receiver, receiver.isReceiverAvailable(receiver, msg), msg);
    }

    /**
     * Sends a message from sender to a group.
     */
    public void sendMsg(String msg, Sender sender, Group group) {
        pushMsgToUserQueue(sender, true, msg);
        pushMsgToUserQueue(group.getReceiver(), group.getReceiver().isReceiverAvailable(group.getReceiver(), msg), msg);
    }

    private void pushMsgToUserQueue(SystemUser user, boolean online, String msg) {
        Queue<MessagingQueue> userQueue = getUserQueueById(user);
        // For demonstration, just add the message string to the queue
        userQueue.offer(new MessagingQueue(user));
    }

    private Queue<MessagingQueue> getUserQueueById(SystemUser user) {
        if (user.getActiveQueue() != null) {
            return user.getActiveQueue();
        }
        // Return a new queue with a single MessagingQueue instance as placeholder
        Queue<MessagingQueue> queue = new java.util.LinkedList<>();
        queue.add(new MessagingQueue(user));
        return queue;
    }

    /**
     * Placeholder for heartbeat request logic.
     */
    public void getHeartbeatRequest(Sender user) {
        // Heartbeat logic would go here
        Queue<MessagingQueue> userQueue = getUserQueueById(user);
        broadcastMsg(userQueue);
    }

    private void broadcastMsg(Queue<MessagingQueue> userQueue) {
        // Placeholder for broadcasting messages to all users in the queue
    }
}

