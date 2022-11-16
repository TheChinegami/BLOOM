package com.example.bloom.Model;

public abstract class Person {
    // attributes
    private String cin;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    // Constructors
    public Person(){};

    public Person(String cin, String firstName, String lastName, String phoneNumber) {
        this.cin = cin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    // getters
    public String getCin() {
        return cin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // setters
    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // to add a new person
    public abstract void add();

    // to update a person
    public abstract void update();

    // to delete a person
    public abstract void delete();

}
