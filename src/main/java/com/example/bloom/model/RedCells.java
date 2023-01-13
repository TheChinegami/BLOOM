package com.example.bloom.model;

import com.example.bloom.functional.CustomException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RedCells extends Bag{

    LocalDate expirationdate ;

    // constructor
    public RedCells() {
    }

    public RedCells(int id, LocalDate donationDate, int donorid, String bagType, String bloodType) {
        super(id, donationDate, donorid, bagType, bloodType);
    }
    public RedCells(int id, LocalDate donationDate, int donorid, String bagType, String bloodType , LocalDate expirationdate) {
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
            LocalDate myDate = this.getDonationDate().plus(42, ChronoUnit.DAYS);
            return myDate;
        }
    }


}