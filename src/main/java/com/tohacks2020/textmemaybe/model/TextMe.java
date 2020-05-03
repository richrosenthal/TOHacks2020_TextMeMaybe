package com.tohacks2020.textmemaybe.model;

import  java.time.LocalDate;

public class TextMe {
    private String name;
    private String email;
    private String password;
    private LocalDate dateCreated;
    private String phoneNumber;
    private String message;

//    public TextMe(String name, String email, String password, LocalDate dateCreated, String phoneNumber, String message) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.dateCreated = dateCreated;
//        this.phoneNumber = phoneNumber;
//        this.message = message;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
