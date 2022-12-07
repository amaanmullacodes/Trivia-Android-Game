package com.team11.UTATrivia.repository;


import com.team11.UTATrivia.models.User;


public class CurrentDatabase {

    private static User user;


    public static User getUser() {
        return user;
    }


    public static void setUser(User user) {
        CurrentDatabase.user = user;
    }

}