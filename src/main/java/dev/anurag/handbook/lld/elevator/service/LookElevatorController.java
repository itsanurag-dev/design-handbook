package dev.anurag.handbook.lld.elevator.service;

import dev.anurag.handbook.lld.elevator.model.*;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-safe Elevator Controller implementing the SCAN/LOOK directional scheduling algorithm.
 * Eliminates CPU busy-waiting using ReentrantLock and Condition variables.
 */
public class LookElevatorController {

    private final int minFloor;
    private final int maxFloor;

    private int currentFloor;
    private Direction currentDirection;
    private ElevatorState currentState;

    private final TreeSet<Integer> upStops = new TreeSet<>();
    private final TreeSet<Integer> downStops = new TreeSet<>(Collections.reverseOrder());

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition hasWorkCondition = lock.newCondition();

    public LookElevatorController(int minFloor, int maxFloor, int initialFloor) {
        if (minFloor >= maxFloor) {
            throw new IllegalArgumentException("minFloor must be strictly less than maxFloor");
        }
        if (initialFloor < minFloor || initialFloor > maxFloor) {
            throw new IllegalArgumentException("initialFloor out of bounds");
        }
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.currentFloor = initialFloor;
        this.currentDirection = Direction.NONE;
        this.currentState = ElevatorState.IDLE;
    }

    public void submitRequest(Request request) {
        lock.lock();
        try {
            int target = request.getTargetFloor();
            validateFloor(target);

            if (target == currentFloor && currentState == ElevatorState.IDLE) {
                return;
            }

            if (target >= currentFloor) {
                upStops.add(target);
            } else {
                downStops.add(target);
            }

            hasWorkCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public boolean step() throws InterruptedException {
        lock.lock();
        try {
            while (upStops.isEmpty() && downStops.isEmpty()) {
                currentState = ElevatorState.IDLE;
                currentDirection = Direction.NONE;
                hasWorkCondition.await();
            }

            determineDirection();

            if (currentDirection == Direction.UP) {
                Integer nextFloor = upStops.ceiling(currentFloor);
                if (nextFloor == null) {
                    currentDirection = Direction.DOWN;
                    return true;
                }

                if (currentFloor == nextFloor) {
                    upStops.remove(nextFloor);
                    openAndCloseDoors();
                } else {
                    currentFloor++;
                    currentState = ElevatorState.MOVING;
                }
            } else if (currentDirection == Direction.DOWN) {
                Integer nextFloor = downStops.floor(currentFloor);
                if (nextFloor == null) {
                    currentDirection = Direction.UP;
                    return true;
                }

                if (currentFloor == nextFloor) {
                    downStops.remove(nextFloor);
                    openAndCloseDoors();
                } else {
                    currentFloor--;
                    currentState = ElevatorState.MOVING;
                }
            }

            return true;
        } finally {
            lock.unlock();
        }
    }

    private void determineDirection() {
        if (currentDirection == Direction.NONE) {
            if (!upStops.isEmpty()) {
                currentDirection = Direction.UP;
            } else if (!downStops.isEmpty()) {
                currentDirection = Direction.DOWN;
            }
        }
    }

    private void openAndCloseDoors() {
        currentState = ElevatorState.DOOR_OPEN;
        currentState = ElevatorState.IDLE;
    }

    private void validateFloor(int floor) {
        if (floor < minFloor || floor > maxFloor) {
            throw new IllegalArgumentException(
                String.format("Floor %d is out of range [%d, %d]", floor, minFloor, maxFloor));
        }
    }

    public int getCurrentFloor() {
        lock.lock();
        try { return currentFloor; } finally { lock.unlock(); }
    }

    public Direction getCurrentDirection() {
        lock.lock();
        try { return currentDirection; } finally { lock.unlock(); }
    }

    public ElevatorState getCurrentState() {
        lock.lock();
        try { return currentState; } finally { lock.unlock(); }
    }

    public Set<Integer> getUpStops() {
        lock.lock();
        try { return Collections.unmodifiableSet(new TreeSet<>(upStops)); } finally { lock.unlock(); }
    }

    public Set<Integer> getDownStops() {
        lock.lock();
        try { return Collections.unmodifiableSet(new TreeSet<>(downStops)); } finally { lock.unlock(); }
    }
}
