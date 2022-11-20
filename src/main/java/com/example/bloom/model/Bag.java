package com.example.bloom.model;

import com.example.bloom.functional.CustomException;

import java.time.LocalDate;

public class Bag
{
    // attributes
    private int id;
    private LocalDate DonationDate;
    private String donatorCin;
    private String BloodType;
    private LocalDate expirationDate;

    // constructor
    public Bag() {
    }

    public Bag(int id, LocalDate donationDate, String donatorCin, String bloodType, LocalDate expirationDate) {
        this.id = id;
        DonationDate = donationDate;
        this.donatorCin = donatorCin;
        BloodType = bloodType;
        this.expirationDate = expirationDate;
    }

    // setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDonationDate() {
        return DonationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        DonationDate = donationDate;
    }

    public String getDonatorCin() {
        return donatorCin;
    }

    public void setDonatorCin(String donatorCin) {
        this.donatorCin = donatorCin;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }

    public LocalDate getExpirationDate() throws CustomException {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) throws CustomException {
        this.expirationDate = expirationDate;
    }

    // add new bag
    public void add() {

    }

    // update a bag
    public void update() {

    }

    // delete a bag
    public void delete() {

    }

}
