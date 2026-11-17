package dev.anurag.handbook.patterns.creational.abstractfactory;

public class ModernFurnitureFactory implements FurnitureFactory {
    public Chair createChair() {
        return new ModernChair();
    }
    public Table createTable() {
        return new ModernTable();
    }
}
