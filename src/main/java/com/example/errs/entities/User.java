package com.example.errs.entities;

import java.io.Serializable;

public class User implements Serializable {
    // Variables declaration
    private int idUser;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;

    // Constructors
    public User() {
    }

    public User(int idUser) {
        this.idUser = idUser;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String fullName, String email, String phoneNumber, String username, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    public User(int idUser, String fullName, String email, String phoneNumber, String username, String password) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    // Getters & Setters

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
