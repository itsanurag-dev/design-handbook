package dev.anurag.handbook.patterns.behavioral.visitor;

public interface ItemElement {
    int accept(ShoppingCartVisitor visitor);
}
