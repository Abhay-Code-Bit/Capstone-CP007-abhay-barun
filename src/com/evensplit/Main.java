
package com.evensplit;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("       WELCOME TO EVENSPLIT");
        System.out.println("  Group Expense Settlement Tool");
        System.out.println("================================");

        // Create group
        Group group = new Group(1, "Travel expenses");

        // Create members
        Person person1 = new Person(1, "Abhay");
        Person person2 = new Person(2, "Navya");
        Person person3 = new Person(3, "Satyam");
        Person person4 = new Person(4, "Aarya");

        // Add members to group
        group.addMember(person1);
        group.addMember(person2);
        group.addMember(person3);
        group.addMember(person4);

        // Create expenses
        Expense expense1 = new Expense(
                1, "Hotel", 1000, person1, LocalDate.now()
        );

        Expense expense2 = new Expense(
                2, "Food", 500, person2, LocalDate.now()
        );

        Expense expense3 = new Expense(
                3, "Transport", 300, person3, LocalDate.now()
        );

        Expense expense4 = new Expense(
                4, "Snacks", 200, person4, LocalDate.now()
        );

        // Add expenses
        group.addExpense(expense1);
        group.addExpense(expense2);
        group.addExpense(expense3);
        group.addExpense(expense4);

        // Display group details
        group.displayGroupDetails();

        // Calculate settlements
        SettlementManager manager = new SettlementManager();

        List<Settlement> settlements =
                manager.calculateSettlements(group);

        // Display settlement plan
        manager.displaySettlements(settlements);
    }
}
