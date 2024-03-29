package com.example.bloom.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Person {
    // attributes
    private int id;
    private String cin;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    // Constructors


//    private final IntegerProperty idProperty = new SimpleIntegerProperty();
//    private final StringProperty cinProperty = new SimpleStringProperty();
//    private final StringProperty firstnameProperty = new SimpleStringProperty();
//    private final StringProperty lastnameProperty = new SimpleStringProperty();
//    private final StringProperty phoneNumberProperty = new SimpleStringProperty();


    public Person(){};

    public Person(int id, String cin, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.cin = cin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }


    // getters

    // setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

//    public IntegerProperty idProperty(){
//
//    }
}


