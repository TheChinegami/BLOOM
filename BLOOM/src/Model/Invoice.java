package Model;

import java.util.Date;

public class Invoice {
    // attributes
    private int id;
    private Date invoideDate;
    private double amount;
    private int idClient;

    // constructor
    public Invoice() {
    }

    public Invoice(int id, Date invoideDate, double amount, int idClient) {
        this.id = id;
        this.invoideDate = invoideDate;
        this.amount = amount;
        this.idClient = idClient;
    }

    // getters
    public int getId() {
        return id;
    }

    public Date getInvoideDate() {
        return invoideDate;
    }

    public double getAmount() {
        return amount;
    }

    public int getIdClient() {
        return idClient;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setInvoideDate(Date invoideDate) {
        this.invoideDate = invoideDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    // add new invoice
    public void add(Invoice i) {

    }

    // delete an invoice
    public void delete(int id) {

    }
}
