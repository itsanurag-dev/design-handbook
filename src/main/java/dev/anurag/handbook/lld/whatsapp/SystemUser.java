package dev.anurag.handbook.lld.whatsapp;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Represents a system user in WhatsApp.
 */
public class SystemUser {
    private int userId;
    private String name;

    /**
     * Gets the user ID.
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     * @param userId the user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the user's name.
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     * @param name the user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the active messaging queue for the user.
     * This is placeholder logic; in a real system, this would return the user's actual queue(s).
     * @return a queue containing a single MessagingQueue instance for this user
     */
    public Queue<MessagingQueue> getActiveQueue() {
        Queue<MessagingQueue> queue = new LinkedList<>();
        queue.add(new MessagingQueue(this));
        return queue;
    }
}
