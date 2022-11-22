package com.example.bloom.model;

import com.example.bloom.functional.CustomException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RedCells extends Bag{

    // constructor
    public RedCells() {
    }

    public RedCells(int id, LocalDate donationDate, String cin, String bloodType, LocalDate expirationDate) {
        super(id, donationDate, cin, bloodType, expirationDate);
    }

    // calculate expiration date
    @Override
    public void setExpirationDate(LocalDate expirationDate) throws CustomException {
        throw new CustomException("this attribute is calculated! you must insert the donationDate");
    }

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
