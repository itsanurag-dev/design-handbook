package dev.anurag.handbook.concurrency.ratelimiter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-safe Token Bucket Rate Limiter using lazy mathematical refill.
 * Guarantees zero background threads and precise rate enforcement.
 */
public class TokenBucketRateLimiter {

    private final long capacity;
    private final double refillRatePerNano;

    private double availableTokens;
    private long lastRefillNano;
    private final ReentrantLock lock = new ReentrantLock();

    public TokenBucketRateLimiter(long capacity, double refillTokensPerSecond) {
        if (capacity <= 0 || refillTokensPerSecond <= 0) {
            throw new IllegalArgumentException("Capacity and refill rate must be positive");
        }
        this.capacity = capacity;
        this.refillRatePerNano = refillTokensPerSecond / 1_000_000_000.0;
        this.availableTokens = capacity;
        this.lastRefillNano = System.nanoTime();
    }

    public boolean tryAcquire() {
        return tryAcquire(1);
    }

    public boolean tryAcquire(long tokens) {
        if (tokens <= 0) {
            throw new IllegalArgumentException("Tokens to acquire must be positive: " + tokens);
        }

        lock.lock();
        try {
            refill();

            if (availableTokens >= tokens) {
                availableTokens -= tokens;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void refill() {
        long now = System.nanoTime();
        long elapsedNanos = now - lastRefillNano;

        if (elapsedNanos > 0) {
            double tokensToAdd = elapsedNanos * refillRatePerNano;
            availableTokens = Math.min(capacity, availableTokens + tokensToAdd);
            lastRefillNano = now;
        }
    }

    public double getAvailableTokens() {
        lock.lock();
        try {
            refill();
            return availableTokens;
        } finally {
            lock.unlock();
        }
    }
}

