package com.example.projectrevange.models;

import java.util.Date;

public class Revenge {

    private String id, title,reason,userIdFrom,userIdTo,howRevenge;
    private Date date;

    public Revenge() {
    }

    public Revenge(String id, String title, String reason, String userIdFrom,
                   String userIdTo, String howRevenge, Date date) {
        this.id = id;
        this.title = title;
        this.reason = reason;
        this.userIdFrom = userIdFrom;
        this.userIdTo = userIdTo;
        this.howRevenge = howRevenge;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(String userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public String getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(String userIdTo) {
        this.userIdTo = userIdTo;
    }

    public String getHowRevenge() {
        return howRevenge;
    }

    public void setHowRevenge(String howRevenge) {
        this.howRevenge = howRevenge;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Revenge{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", reason='" + reason + '\'' +
                ", userIdFrom='" + userIdFrom + '\'' +
                ", userIdTo='" + userIdTo + '\'' +
                ", howRevenge='" + howRevenge + '\'' +
                ", date=" + date +
                '}';
    }
}
