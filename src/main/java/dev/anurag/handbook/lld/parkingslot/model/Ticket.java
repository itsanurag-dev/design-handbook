package dev.anurag.handbook.lld.parkingslot.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class Ticket {
    private final String ticketId;
    private final String licensePlate;
    private final int floorNumber;
    private final int spotNumber;
    private final VehicleType spotType;
    private final Instant issuedAt;

    public Ticket(String ticketId, String licensePlate, int floorNumber, int spotNumber, VehicleType spotType, Instant issuedAt) {
        this.ticketId = ticketId;
        this.licensePlate = licensePlate;
        this.floorNumber = floorNumber;
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.issuedAt = issuedAt;
    }

    public static Ticket issue(String licensePlate, ParkingSpot spot) {
        return new Ticket(
            UUID.randomUUID().toString(),
            licensePlate,
            spot.getFloorNumber(),
            spot.getSpotNumber(),
            spot.getType(),
            Instant.now()
        );
    }

    public String ticketId() { return ticketId; }
    public String licensePlate() { return licensePlate; }
    public int floorNumber() { return floorNumber; }
    public int spotNumber() { return spotNumber; }
    public VehicleType spotType() { return spotType; }
    public Instant issuedAt() { return issuedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return floorNumber == ticket.floorNumber &&
               spotNumber == ticket.spotNumber &&
               Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, floorNumber, spotNumber);
    }
}
