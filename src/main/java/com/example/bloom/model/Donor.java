package com.example.bloom.model;

public class Donor extends Person {
    // attributes

    private String birthDate;
    // constructor
    public Donor() {
    }

    public Donor(int id, String cin, String firstName, String lastName, String phoneNumber, String birthDate) {
        super(id,cin, firstName, lastName, phoneNumber);
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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
                ", birth date : "+this.birthDate;
    }
}
