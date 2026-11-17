package dev.anurag.handbook.patterns.creational.singleton;

import java.io.Serializable;

public class DoublyCheckedSingleton implements Serializable {

    private static final long serialVersionUID = 1L;
    private static volatile DoublyCheckedSingleton instance;

    private DoublyCheckedSingleton() {
        if (instance != null) {
            throw new IllegalStateException("Instance already initialized. Use getInstance().");
        }
    }

    public static DoublyCheckedSingleton getInstance() {
        if (instance == null) {
            synchronized (DoublyCheckedSingleton.class) {
                if (instance == null) {
                    instance = new DoublyCheckedSingleton();
                }
            }
        }
        return instance;
    }

    protected Object readResolve() {
        return getInstance();
    }
}
