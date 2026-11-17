
package com.evensplit;

import java.time.LocalDate;

public class Expense {

    private int expenseId;
    private String description;
    private double amount;
    private Person paidBy;
    private LocalDate date;

    // Constructor
    public Expense(int expenseId, String description,
                   double amount, Person paidBy,
                   LocalDate date) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }

        if (paidBy == null || date == null) {
            throw new IllegalArgumentException("Person and date are required.");
        }

        this.expenseId = expenseId;
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.date = date;
    }

    // Getters
    public int getExpenseId() {
        return expenseId;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public Person getPaidBy() {
        return paidBy;
    }

    public LocalDate getDate() {
        return date;
    }

    // Display expense details
    @Override
    public String toString() {
        return "Expense ID: " + expenseId
                + ", Description: " + description
                + ", Amount: Rs. " + amount
                + ", Paid By: " + paidBy.getName()
                + ", Date: " + date;
    }
}
