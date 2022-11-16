package com.example.bloom.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Plasma extends Bag{
    // attributes
    private Date expirationDate;

    // constructor
    public Plasma() {
    }

    public Plasma(int id, String donatorCin, Date donnationDate, String bloodType, double price, Date expirationDate) {
        super(id, donatorCin, donnationDate, bloodType, price);
        this.expirationDate = expirationDate;
    }

    // getters
    public Date getExpirationDate() {
        return expirationDate;
    }

    // setters
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    // calculate expiration date
    public void calculateExpirationDate(Date current){
        Calendar c = new GregorianCalendar(current.getYear(),current.getMonth(),current.getDay());
        c.add(Calendar.YEAR,1);
        Date newDate = c.getTime();
        setExpirationDate(newDate);
    }
}
