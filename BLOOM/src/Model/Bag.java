package Model;

import java.util.Date;

public class Bag {
    // attributes
    private int id;
    private String donatorCin;
    private Date DonationDate;
    private String BloodType;
    private double price;

    // constructor
    public Bag() {
    }

    public Bag(int id, String donatorCin, Date donationDate, String bloodType, double price) {
        this.id = id;
        this.donatorCin = donatorCin;
        DonationDate = donationDate;
        BloodType = bloodType;
        this.price = price;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getDonatorCin() {
        return donatorCin;
    }

    public Date getDonationDate() {
        return DonationDate;
    }

    public String getBloodType() {
        return BloodType;
    }

    public double getPrice() {
        return price;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDonatorCin(String donatorCin) {
        this.donatorCin = donatorCin;
    }

    public void setDonationDate(Date donnationDate) {
        DonationDate = donnationDate;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }

    public void setPrice(double price) {
        this.price = price;
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
