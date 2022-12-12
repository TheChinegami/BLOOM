package com.example.bloom.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Bill {
    // attributes
    private int id;
    private LocalDate billDate;
    private double amount;
    private int idClient;
    private ArrayList<Bag> bagList;

    // constructor
    public Bill() {
    }

    public Bill(int id, LocalDate billDate, double amount, int idClient) {
        this.id = id;
        this.billDate = billDate;
        this.amount = amount;
        this.idClient = idClient;
        bagList = new ArrayList();
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public ArrayList<Bag> getBagList() {
        return bagList;
    }

    public void setBagList(ArrayList<Bag> bagList) {
        this.bagList = bagList;
    }

    // add new invoice
    public void add(Bill i) {

    }

    // delete an invoice
    public void delete(int id) {

    }
}
