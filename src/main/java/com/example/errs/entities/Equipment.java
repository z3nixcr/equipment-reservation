package com.example.errs.entities;

import java.io.Serializable;

public class Equipment implements Serializable {
    // Variables declaration
    private int idEquipment;
    private String name;
    private int inStock;
    private float price;
    private boolean availability;

    // Constructors
    public Equipment() {
    }

    public Equipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    public Equipment(int idEquipment, String name, int inStock, float price, boolean availability) {
        this.idEquipment = idEquipment;
        this.name = name;
        this.inStock = inStock;
        this.price = price;
        this.availability = availability;
    }

    // Getters & Setters
    public int getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
