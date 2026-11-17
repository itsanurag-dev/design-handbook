package dev.anurag.handbook.lld.elevator.model;

public final class InternalRequest implements Request {
    private final int destinationFloor;

    public InternalRequest(int destinationFloor) {
        if (destinationFloor < 0) {
            throw new IllegalArgumentException("Floor cannot be negative: " + destinationFloor);
        }
        this.destinationFloor = destinationFloor;
    }

    @Override
    public int getTargetFloor() {
        return destinationFloor;
    }

    @Override
    public Direction getDirection() {
        return Direction.NONE;
    }

    @Override
    public String toString() {
        return "InternalRequest{toFloor=" + destinationFloor + "}";
    }
}
