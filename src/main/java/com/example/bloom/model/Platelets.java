package com.example.bloom.model;

import com.example.bloom.functional.CustomException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Platelets extends Bag{

    // constructor
    public Platelets() {
    }

    public Platelets(int id, LocalDate donationDate, String donatorCin, String bloodType, LocalDate expirationDate) {
        super(id, donationDate, donatorCin, bloodType, expirationDate);
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
            LocalDate myDate = this.getDonationDate().plus(5, ChronoUnit.DAYS);
            return myDate;
        }
    }
}
