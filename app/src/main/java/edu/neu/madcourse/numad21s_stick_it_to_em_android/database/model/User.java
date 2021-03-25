package edu.neu.madcourse.numad21s_stick_it_to_em_android.database.model;

import java.util.ArrayList;

public class User {
    public String username;
    public String email;
    public ArrayList<String> sentStickers;
    public ArrayList<String> receivedStickers;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username) {
        this.username = username;
        sentStickers = new ArrayList<>();
        receivedStickers = new ArrayList<>();
    }

    // another constructor for future use
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        sentStickers = new ArrayList<>();
        receivedStickers = new ArrayList<>();
    }
}
