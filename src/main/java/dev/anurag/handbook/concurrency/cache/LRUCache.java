package dev.anurag.handbook.concurrency.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Thread-safe Least Recently Used (LRU) Cache supporting O(1) reads, writes, and evictions.
 * Concurrency is managed via fine-grained ReentrantReadWriteLock.
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public class LRUCache<K, V> {

    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> prev;
        private Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<K, Node<K, V>> map;
    private final Node<K, V> head;
    private final Node<K, V> tail;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive: " + capacity);
        }
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);

        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public Optional<V> get(K key) {
        lock.writeLock().lock();
        try {
            Node<K, V> node = map.get(key);
            if (node == null) {
                return Optional.empty();
            }
            moveToHead(node);
            return Optional.of(node.value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            Node<K, V> existing = map.get(key);
            if (existing != null) {
                existing.value = value;
                moveToHead(existing);
                return;
            }

            if (map.size() >= capacity) {
                Node<K, V> lru = removeTail();
                if (lru != null) {
                    map.remove(lru.key);
                }
            }

            Node<K, V> newNode = new Node<>(key, value);
            map.put(key, newNode);
            addFirst(newNode);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        lock.readLock().lock();
        try {
            return map.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    private void addFirst(Node<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node<K, V> node) {
        removeNode(node);
        addFirst(node);
    }

    private Node<K, V> removeTail() {
        if (tail.prev == head) {
            return null;
        }
        Node<K, V> lru = tail.prev;
        removeNode(lru);
        return lru;
    }
}
