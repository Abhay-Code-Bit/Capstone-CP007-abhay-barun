
package com.evensplit;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private int groupId;
    private String groupName;
    private List<Person> members;
    private List<Expense> expenses;

    // Constructor
    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.members = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    // Add a member
    public void addMember(Person person) {

        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null.");
        }

        for (Person member : members) {
            if (member.getId() == person.getId()) {
                throw new IllegalArgumentException("Member ID already exists.");
            }
        }

        members.add(person);
    }

    // Add an expense
    public void addExpense(Expense expense) {

        if (expense == null) {
            throw new IllegalArgumentException("Expense cannot be null.");
        }

        boolean memberExists = false;

        for (Person member : members) {
            if (member.getId() == expense.getPaidBy().getId()) {
                memberExists = true;
                break;
            }
        }

        if (!memberExists) {
            throw new IllegalArgumentException(
                    "The person paying must belong to this group."
            );
        }

        expenses.add(expense);
    }

    // Getters
    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Person> getMembers() {
        return new ArrayList<>(members);
    }

    public List<Expense> getExpenses() {
        return new ArrayList<>(expenses);
    }

    // Display group details
    public void displayGroupDetails() {

        System.out.println("\nGroup: " + groupName);
        System.out.println("Group ID: " + groupId);

        System.out.println("\nMembers:");

        for (Person member : members) {
            System.out.println(member);
        }

        System.out.println("\nExpenses:");

        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }
}
