package com.example.bloom.Model;

public class InvoiceRow {
    // attributes
    private int invoiceId;
    private int bagId;
    private int quantity;

    // constuctor
    public InvoiceRow() {
    }

    public InvoiceRow(int invoiceId, int bagId, int quantity) {
        this.invoiceId = invoiceId;
        this.bagId = bagId;
        this.quantity = quantity;
    }

    // getters
    public int getInvoiceId() {
        return invoiceId;
    }

    public int getBagId() {
        return bagId;
    }

    public int getQuantity() {
        return quantity;
    }

    // setters
    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setBagId(int bagId) {
        this.bagId = bagId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // add new invoice row
    public void add(InvoiceRow ir) {

    }

    // delete an invoice row
    public void delete(int InvoiceId,int bagId) {

    }
}
