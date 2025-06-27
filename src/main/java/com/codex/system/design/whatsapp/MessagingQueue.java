package com.codex.system.design.whatsapp;

import java.util.*;

/**
 * Messaging queue for WhatsApp system users.
 */
public class MessagingQueue implements Queue<String> {
    private final Queue<String> messages;

    public MessagingQueue(SystemUser user) {
        this.messages = new LinkedList<>();
    }

    @Override
    public int size() {
        return messages.size();
    }

    @Override
    public boolean isEmpty() {
        return messages.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return messages.contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return messages.iterator();
    }

    @Override
    public Object[] toArray() {
        return messages.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return messages.toArray(a);
    }

    @Override
    public boolean add(String e) {
        return messages.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return messages.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return messages.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return messages.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return messages.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return messages.retainAll(c);
    }

    @Override
    public void clear() {
        messages.clear();
    }

    @Override
    public boolean offer(String e) {
        return messages.offer(e);
    }

    @Override
    public String remove() {
        return messages.remove();
    }

    @Override
    public String poll() {
        return messages.poll();
    }

    @Override
    public String element() {
        return messages.element();
    }

    @Override
    public String peek() {
        return messages.peek();
    }
}
