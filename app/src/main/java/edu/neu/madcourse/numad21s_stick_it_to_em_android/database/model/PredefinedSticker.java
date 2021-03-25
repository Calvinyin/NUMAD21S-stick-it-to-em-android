package edu.neu.madcourse.numad21s_stick_it_to_em_android.database.model;

import java.util.HashMap;
import java.util.Map;

public class PredefinedSticker {

    public Map<String, String> stickerMap;

    public PredefinedSticker() {
        stickerMap = new HashMap<>();

        stickerMap.put("Grin", new String(Character.toChars(0x1F601)));
        stickerMap.put("TearOfJoy", new String(Character.toChars(0x1F602)));
        stickerMap.put("Smile", new String(Character.toChars(0x1F603)));
        stickerMap.put("Heart", new String(Character.toChars(0x1F60D)));
        stickerMap.put("Sleepy", new String(Character.toChars(0x1F62A)));
        stickerMap.put("Dizzy", new String(Character.toChars(0x1F635)));
    }

    public Map<String, String> getStickerMap() {
        return stickerMap;
    }
}
