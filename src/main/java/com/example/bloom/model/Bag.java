package com.example.bloom.model;

import com.example.bloom.functional.CustomException;

import java.time.LocalDate;

public class Bag
{
    // attributes
    private int id;
    private LocalDate donationDate;
    private String donorCin;
    private String bagType;
    private String bloodType;

    private LocalDate expirationDate;

    // constructor
    public Bag() {
    }

    public Bag(int id, LocalDate donationDate, String donorCin, String bagType, String bloodType) {
        this.id = id;
        this.donationDate = donationDate;
        this.donorCin = donorCin;
        this.bagType = bagType;
        this.bloodType = bloodType;
    }

    // setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    public String getDonorCin() {
        return donorCin;
    }

    public void setDonorCin(String donorCin) {
        this.donorCin = donorCin;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getBagType() {
        return bagType;
    }

    public void setBagType(String bagType) {
        this.bagType = bagType;
    }

    public LocalDate getExpirationDate() throws CustomException {
        throw new CustomException("you don't have the expiration date yet");
    }

    public void setExpirationDate(LocalDate expirationDate) throws CustomException {
        throw new CustomException("this attribute is calculated! you must insert the donationDate");
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
