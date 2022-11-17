package com.example.bloom.Model;

import java.util.Date;

public class Platelets extends Bag{

    // constructor
    public Platelets() {
    }

    public Platelets(int id, Date donationDate, String donatorCin, String bloodType, Date expirationDate) {
        super(id, donationDate, donatorCin, bloodType, expirationDate);
    }

    // calculate expiration date
    @Override
    public void setExpirationDate(Date expirationDate) {
        super.setExpirationDate(getExpirationDate());
    }

    @Override
    public Date getExpirationDate() {
//        Calendar c = new GregorianCalendar(current.getYear(),current.getMonth(),current.getDay());
//        c.add(Calendar.YEAR,1);
//        Date newDate = c.getTime();
//        return newDate;
        return getExpirationDate();
    }
}
