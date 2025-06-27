package com.codex.system.design.elevator;

/**
 * Worker thread to start the elevator processing loop.
 */
public class ProcessJobWorker implements Runnable {
    private final Elevator elevator;

    public ProcessJobWorker(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void run() {
        elevator.startElevator();
    }
}