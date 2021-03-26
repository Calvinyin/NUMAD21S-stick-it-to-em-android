package edu.neu.madcourse.numad21s_stick_it_to_em_android.database.model;

import java.util.ArrayList;

public class User {
    public static final String PLACE_HOLDER_STICKER = "place_holder";

    public String mUserName;
    public String email;
    public ArrayList<String> sentStickers;
    public ArrayList<String> receivedStickers;

    public User() {}

    public User(String userName) {
        this.mUserName = userName;
        sentStickers = new ArrayList<>();
        sentStickers.add(PLACE_HOLDER_STICKER);
        receivedStickers = new ArrayList<>();
        receivedStickers.add(PLACE_HOLDER_STICKER);
    }

    public User(String userName, String email) {
        this.mUserName = userName;
        this.email = email;
        sentStickers = new ArrayList<>();
        sentStickers.add(PLACE_HOLDER_STICKER);
        receivedStickers = new ArrayList<>();
        receivedStickers.add(PLACE_HOLDER_STICKER);
    }
}
