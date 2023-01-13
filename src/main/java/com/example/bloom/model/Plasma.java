package com.example.bloom.model;

import com.example.bloom.functional.CustomException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Plasma extends Bag{

    LocalDate expirationdate ;

    // constructor
    public Plasma() {
    }

    public Plasma(int id, LocalDate donationDate, int donorid, String bagType, String bloodType) {
        super(id, donationDate, donorid, bagType,bloodType);
    }
    public Plasma(int id, LocalDate donationDate, int donorid, String bagType, String bloodType , LocalDate expirationdate) {
        super(id, donationDate, donorid, bagType, bloodType);
        this.expirationdate=expirationdate ;
    }

    // calculate expiration date

    @Override
    public LocalDate getExpirationDate() throws CustomException
    {
        if(getDonationDate()==null)
        {
            throw new CustomException("you didn't set the donation date yet!");
        }else
        {
            LocalDate myDate = this.getDonationDate().plus(365, ChronoUnit.DAYS);
            return myDate;
        }
    }
}