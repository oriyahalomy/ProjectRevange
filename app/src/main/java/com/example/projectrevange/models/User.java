package com.example.projectrevange.models;

import org.jetbrains.annotations.NotNull;

public class User {
    /// unique id of the user
    private String uid;

    private String email;
    private String password;
    private String fName;
    private String lName;
    private String phone;

    private boolean isAdmin;

    public User() {
    }

    public User(String uid, String email, String password, String fName, String lName, String phone, boolean isAdmin) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.isAdmin = isAdmin;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", phone='" + phone + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
