package com.example.trucksharingapplication;

import android.graphics.Bitmap;

public class User {
    int userid;
    String username;
    String password;
    Bitmap accountImage;
    String fullName;
    String phoneNumber;

    public User(String username, String password, Bitmap accountImage, String fullName, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.accountImage = accountImage;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public Bitmap getAccountImage() { return accountImage; }

    public void setAccountImage(Bitmap accountImage) { this.accountImage = accountImage; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
