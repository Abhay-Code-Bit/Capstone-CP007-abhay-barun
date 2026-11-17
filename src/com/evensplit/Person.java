package com.evensplit;

public class Person {

    private int id;
    private String name;

    // Constructor
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    // Display person details
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }
}