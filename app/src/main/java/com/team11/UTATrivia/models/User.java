package com.team11.UTATrivia.models;

import java.io.Serializable;

/**
 * The type User.
 */
public class User implements Serializable {

    private String userId;
    private String name;
    private String emailAddress;
    private String password;
    private String address;
    private String onlineStatus;
    private Integer points;
    private Integer level;


    public User() {
    }


    public User(String name, String emailAddress, String password, String address,
                String onlineStatus, Integer points, Integer level) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
        this.address = address;
        this.onlineStatus = onlineStatus;
        this.points = points;
        this.level = level;
    }


    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmailAddress() {
        return emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getOnlineStatus() {
        return onlineStatus;
    }


    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }


    public Integer getPoints() {
        return points;
    }


    public void setPoints(Integer points) {
        this.points = points;
    }


    public Integer getLevel() {
        return level;
    }


    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", onlineStatus='" + onlineStatus + '\'' +
                ", points='" + points + '\'' +
                ", level=" + level +
                '}';
    }
}
