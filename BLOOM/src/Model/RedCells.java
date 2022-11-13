package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RedCells extends Bag{
    // attributes
    private Date expirationDate;

    // constructor
    public RedCells() {
        setExpirationDate(super.getDonationDate());
    }

    public RedCells(int id, String donatorCin, Date donnationDate, String bloodType, double price, Date expirationDate) {
        super(id, donatorCin, donnationDate, bloodType, price);
        this.expirationDate = expirationDate;
        setExpirationDate(donnationDate);
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
        c.add(Calendar.DAY_OF_MONTH,42);
        Date newDate = c.getTime();
        setExpirationDate(newDate);
    }
}
