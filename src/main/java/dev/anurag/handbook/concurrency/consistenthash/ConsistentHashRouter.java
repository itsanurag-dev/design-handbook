package dev.anurag.handbook.concurrency.consistenthash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Thread-safe Consistent Hashing Router using MD5 32-bit integer truncation
 * and virtual nodes to balance partitions across physical servers.
 *
 * @param <T> Node / Server type
 */
public class ConsistentHashRouter<T> {

    private final int numberOfReplicas;
    private final SortedMap<Integer, T> circle = new TreeMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public ConsistentHashRouter(int numberOfReplicas, Collection<T> nodes) {
        if (numberOfReplicas <= 0) {
            throw new IllegalArgumentException("Replicas must be positive");
        }
        this.numberOfReplicas = numberOfReplicas;
        if (nodes != null) {
            for (T node : nodes) {
                addNode(node);
            }
        }
    }

    public void addNode(T node) {
        lock.writeLock().lock();
        try {
            for (int i = 0; i < numberOfReplicas; i++) {
                int hash = hash(node.toString() + "#VN" + i);
                circle.put(hash, node);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void removeNode(T node) {
        lock.writeLock().lock();
        try {
            for (int i = 0; i < numberOfReplicas; i++) {
                int hash = hash(node.toString() + "#VN" + i);
                circle.remove(hash);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Optional<T> route(String key) {
        lock.readLock().lock();
        try {
            if (circle.isEmpty()) {
                return Optional.empty();
            }

            int hash = hash(key);
            if (!circle.containsKey(hash)) {
                SortedMap<Integer, T> tailMap = circle.tailMap(hash);
                hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
            }
            return Optional.of(circle.get(hash));
        } finally {
            lock.readLock().unlock();
        }
    }

    private int hash(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(key.getBytes(StandardCharsets.UTF_8));
            return ((digest[0] & 0xFF) << 24)
                 | ((digest[1] & 0xFF) << 16)
                 | ((digest[2] & 0xFF) << 8)
                 | (digest[3] & 0xFF);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 digest algorithm not available", e);
        }
    }
}
