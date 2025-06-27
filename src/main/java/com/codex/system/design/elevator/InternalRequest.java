package com.codex.system.design.elevator;

/**
 * Represents an internal request (destination floor) from inside the elevator.
 */
public class InternalRequest {
    private int destinationFloor;

    public InternalRequest(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    @Override
    public String toString() {
        return "The destinationFloor is - " + destinationFloor;
    }
}