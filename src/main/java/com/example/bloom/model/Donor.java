package com.example.bloom.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Donor extends Person {


    // attributes

    private long age;
    private String sickness;
    private String emergencyNumber;
    // constructor
    public Donor() {
    }

    public Donor(int id, String cin, String firstName, String lastName, String phoneNumber, long age, String emergencyNumber, String sickness) {
        super(id, cin, firstName, lastName, phoneNumber);
        this.age = age;
        this.sickness = sickness;
        this.emergencyNumber = emergencyNumber;
    }

    // getters and setters
    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String isSickness() {
        return sickness;
    }

    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    // add new user
    @Override
    public void add() {

    }

    // update a person
    @Override
    public void update() {

    }

    // delete a person
    @Override
    public void delete() {

    }

    @Override
    public String toString() {
        return "id : "+this.getId()+
                ", cin : "+this.getCin()+
                ", first name : "+this.getFirstName()+
                ", last name : "+this.getLastName()+
                ", phone number : "+this.getPhoneNumber()+
                ", birth date : "+this.age+
                ", emergency number : "+this.emergencyNumber+
                ", sickness : "+this.sickness;
    }


}


