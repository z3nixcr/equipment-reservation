package com.example.errs.entities;

import java.io.Serializable;
import java.sql.Date;

public class Reservation implements Serializable {
    private int idReservation;
    private int idUser;
    private int idEquipment;
    private int quantity;
    private String equipmentName;
    private String userName;
    private String userEmail;
    private String phoneNumber;
    private float totalPrice;
    private Date fromDate;
    private Date toDate;

    // Constructors
    public Reservation() {
    }

    public Reservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Reservation(int idUser, int idEquipment, int quantity, Date fromDate, Date toDate) {
        this.idUser = idUser;
        this.idEquipment = idEquipment;
        this.quantity = quantity;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Reservation(int idReservation, int idUser, int idEquipment, String equipmentName, String userName, String userEmail, String phoneNumber, float totalPrice, Date fromDate, Date toDate) {
        this.idReservation = idReservation;
        this.idUser = idUser;
        this.idEquipment = idEquipment;
        this.equipmentName = equipmentName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.phoneNumber = phoneNumber;
        this.totalPrice = totalPrice;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    // Getters & Setters
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
