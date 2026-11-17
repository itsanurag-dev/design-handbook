package dev.anurag.handbook.patterns.behavioral.observer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CricketScore Observer Pattern Tests")
class CricketScoreTest {

    static class TestChannel extends CricketObserver {
        private int currentScore;

        public TestChannel(CricketScore cricketScore) {
            this.cricketscore = cricketScore;
            this.cricketscore.attach(this);
        }

        @Override
        public void updateScore() {
            this.currentScore = cricketscore.getScore();
        }

        public int getCurrentScore() {
            return currentScore;
        }
    }

    @Test
    @DisplayName("Should notify all registered observers with updated score")
    void testObserverNotification() {
        CricketScore cricketScore = new CricketScore();
        TestChannel star = new TestChannel(cricketScore);
        TestChannel sony = new TestChannel(cricketScore);

        cricketScore.setScore(42);

        assertEquals(42, star.getCurrentScore());
        assertEquals(42, sony.getCurrentScore());
    }

    @Test
    @DisplayName("Should stop notifying observer after deregistration")
    void testDeregisterObserver() {
        CricketScore cricketScore = new CricketScore();
        TestChannel star = new TestChannel(cricketScore);
        TestChannel sony = new TestChannel(cricketScore);

        cricketScore.removeObserver(sony);
        cricketScore.setScore(100);

        assertEquals(100, star.getCurrentScore());
        assertEquals(0, sony.getCurrentScore());
    }
}
