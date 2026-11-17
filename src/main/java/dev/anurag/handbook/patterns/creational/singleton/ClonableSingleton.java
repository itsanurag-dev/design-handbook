package dev.anurag.handbook.patterns.creational.singleton;

public class ClonableSingleton implements Cloneable {

    private static final ClonableSingleton instance = new ClonableSingleton();

    private ClonableSingleton() {}

    public static ClonableSingleton getInstance() {
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of ClonableSingleton is disallowed to preserve singleton invariant.");
    }
}
