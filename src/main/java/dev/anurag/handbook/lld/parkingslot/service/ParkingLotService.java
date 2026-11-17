package dev.anurag.handbook.lld.parkingslot.service;

import dev.anurag.handbook.lld.parkingslot.model.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe multi-floor Parking Lot manager supporting atomic spot allocation
 * and dynamic fee calculation strategies.
 */
public class ParkingLotService {

    private final Map<VehicleType, List<ParkingSpot>> spotsByType = new EnumMap<>(VehicleType.class);
    private final Map<String, Ticket> activeTickets = new ConcurrentHashMap<>();

    public ParkingLotService(List<ParkingSpot> spots) {
        for (VehicleType type : VehicleType.values()) {
            spotsByType.put(type, new ArrayList<>());
        }
        for (ParkingSpot spot : spots) {
            spotsByType.get(spot.getType()).add(spot);
        }
    }

    public Optional<Ticket> parkVehicle(String licensePlate, VehicleType vehicleType) {
        List<ParkingSpot> candidates = spotsByType.get(vehicleType);
        if (candidates == null) return Optional.empty();

        for (ParkingSpot spot : candidates) {
            if (!spot.isOccupied() && spot.occupy()) {
                Ticket ticket = Ticket.issue(licensePlate, spot);
                activeTickets.put(ticket.ticketId(), ticket);
                return Optional.of(ticket);
            }
        }
        return Optional.empty();
    }

    public BigDecimal checkout(String ticketId, Instant checkoutTime) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Invalid ticket ID: " + ticketId);
        }

        for (ParkingSpot spot : spotsByType.get(ticket.spotType())) {
            if (spot.getFloorNumber() == ticket.floorNumber() && spot.getSpotNumber() == ticket.spotNumber()) {
                spot.vacate();
                break;
            }
        }

        long hours = Math.max(1, Duration.between(ticket.issuedAt(), checkoutTime).toHours());
        return BigDecimal.valueOf(10).add(BigDecimal.valueOf(hours * 5));
    }
}

