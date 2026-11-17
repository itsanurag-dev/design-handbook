package dev.anurag.handbook.patterns.behavioral.strategy;

public class PayPalPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using PayPal.");
    }
}
