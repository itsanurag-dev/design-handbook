package dev.anurag.handbook.lld.elevator.model;

import java.util.Objects;

public final class ExternalRequest implements Request {
    private final int sourceFloor;
    private final Direction direction;

    public ExternalRequest(int sourceFloor, Direction direction) {
        if (sourceFloor < 0) {
            throw new IllegalArgumentException("Floor cannot be negative: " + sourceFloor);
        }
        this.sourceFloor = sourceFloor;
        this.direction = Objects.requireNonNull(direction, "Direction cannot be null");
    }

    @Override
    public int getTargetFloor() {
        return sourceFloor;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "ExternalRequest{floor=" + sourceFloor + ", dir=" + direction + "}";
    }
}

