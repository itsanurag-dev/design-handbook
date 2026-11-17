package dev.anurag.handbook.lld.elevator.model;

public enum Direction {
    UP, DOWN, NONE;

    public Direction opposite() {
        if (this == UP) return DOWN;
        if (this == DOWN) return UP;
        return NONE;
    }
}
