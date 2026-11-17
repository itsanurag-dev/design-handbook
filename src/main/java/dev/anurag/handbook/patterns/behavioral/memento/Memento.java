package dev.anurag.handbook.patterns.behavioral.memento;

public class Memento {
    private final String text;
    public Memento(String text) { this.text = text; }
    public String getText() { return text; }
}
