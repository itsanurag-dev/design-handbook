package dev.anurag.handbook.patterns.behavioral.observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CricketScore {
    private final List<CricketObserver> observers = new CopyOnWriteArrayList<>();
    private int score;

    public void attach(CricketObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void registerObserver(CricketObserver observer) {
        attach(observer);
    }

    public void removeObserver(CricketObserver observer) {
        observers.remove(observer);
    }

    public void detach(CricketObserver observer) {
        removeObserver(observer);
    }

    public void setScore(int score) {
        this.score = score;
        System.out.println("CricketScore :: Got updated state " + score);
        System.out.println("CricketScore :: Notifying all the observer with score " + score);
        notifyAllObservers();
    }

    public int getScore() {
        return score;
    }

    public void notifyAllObservers() {
        for (CricketObserver observer : observers) {
            observer.updateScore();
        }
    }
}
