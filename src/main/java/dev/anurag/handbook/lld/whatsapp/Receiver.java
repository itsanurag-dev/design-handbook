package dev.anurag.handbook.lld.whatsapp;

/**
 * Represents a receiver in the WhatsApp system.
 */
public class Receiver extends SystemUser {
    private int userId;
    private String name;
    private String city;
    private String country;

    /**
     * Checks if the receiver is available for a message.
     * @param receiver the receiver
     * @param msg the message
     * @return true if available
     */
    public boolean isReceiverAvailable(Receiver receiver, String msg) {
        return true;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
