package com.example.bloom.model;

import com.example.bloom.functional.CustomException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Plasma extends Bag{

    // constructor
    public Plasma() {
    }

    public Plasma(int id, LocalDate donationDate, String donorCin, String bagType, String bloodType) {
        super(id, donationDate, donorCin, bagType,bloodType);
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
