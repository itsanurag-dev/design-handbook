package dev.anurag.handbook.lld.whatsapp;

/**
 * Represents a WhatsApp group with a receiver.
 */
public class Group {
    private Receiver receiver;

    /**
     * Default constructor for Group.
     */
    public Group() {
        // Default constructor
    }

    /**
     * Gets the receiver of the group.
     *
     * @return the receiver
     */
    public Receiver getReceiver() {
        return receiver;
    }

    /**
     * Sets the receiver for the group.
     *
     * @param receiver the receiver to set
     */
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }
}
