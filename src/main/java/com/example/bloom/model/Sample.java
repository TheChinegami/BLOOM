package com.example.bloom.model;

public class Sample
{
    private String sample;
    private Double pricePerUnity;
    private int quantity;
    private Double pricePerRow;

    public Sample(String sample, Double pricePerUnity, int quantity, Double pricePerRow) {
        this.sample = sample;
        this.pricePerUnity = pricePerUnity;
        this.quantity = quantity;
        this.pricePerRow = pricePerRow;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public Double getPricePerUnity() {
        return pricePerUnity;
    }

    public void setPricePerUnity(Double pricePerUnity) {
        this.pricePerUnity = pricePerUnity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerRow() {
        return pricePerRow;
    }

    public void setPricePerRow(Double pricePerRow) {
        this.pricePerRow = pricePerRow;
    }
}