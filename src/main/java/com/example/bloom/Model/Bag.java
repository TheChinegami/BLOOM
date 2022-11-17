package com.example.bloom.Model;

import java.util.Date;

public class Bag
{
    // attributes
    private int id;
    private Date DonationDate;
    private String donatorCin;
    private String BloodType;
    private Date expirationDate;

    // constructor
    public Bag() {
    }

    public Bag(int id, Date donationDate, String donatorCin, String bloodType, Date expirationDate) {
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

    public Date getDonationDate() {
        return DonationDate;
    }

    public void setDonationDate(Date donationDate) {
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
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
