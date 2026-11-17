
package com.evensplit;

public class Settlement {

    private Person from;
    private Person to;
    private double amount;

    // Constructor
    public Settlement(Person from, Person to, double amount) {

        if (from == null || to == null) {
            throw new IllegalArgumentException("People cannot be null.");
        }

        if (from.getId() == to.getId()) {
            throw new IllegalArgumentException(
                    "A person cannot pay themselves."
            );
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                    "Settlement amount must be positive."
            );
        }

        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    // Getters
    public Person getFrom() {
        return from;
    }

    public Person getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }

    // Display settlement
    @Override
    public String toString() {
        return from.getName() + " pays "
                + to.getName() + " : ₹"
                + String.format("%.2f", amount);
    }
}
