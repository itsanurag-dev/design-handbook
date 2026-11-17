package dev.anurag.handbook.patterns.behavioral.strategy;

public class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using Credit Card.");
    }
}
