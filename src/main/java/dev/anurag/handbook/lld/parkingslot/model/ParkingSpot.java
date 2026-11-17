package dev.anurag.handbook.lld.parkingslot.model;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSpot {
    private final int floorNumber;
    private final int spotNumber;
    private final VehicleType type;
    private final AtomicBoolean occupied = new AtomicBoolean(false);

    public ParkingSpot(int floorNumber, int spotNumber, VehicleType type) {
        this.floorNumber = floorNumber;
        this.spotNumber = spotNumber;
        this.type = type;
    }

    public boolean occupy() {
        return occupied.compareAndSet(false, true);
    }

    public boolean vacate() {
        return occupied.compareAndSet(true, false);
    }

    public boolean isOccupied() {
        return occupied.get();
    }

    public int getFloorNumber() { return floorNumber; }
    public int getSpotNumber() { return spotNumber; }
    public VehicleType getType() { return type; }
}
