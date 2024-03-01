package org.example.app.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Prize {
    FiftyK("50K"),
    OneHundredK("100K"),
    TwoHundredK("200K"),
    FiveHundredK("500K"),
    OneMillionK("1000K");

    public static final List<Prize> PRIZE_LIST = new ArrayList<>();

    static {
        PRIZE_LIST.add(Prize.FiftyK);
        PRIZE_LIST.add(Prize.OneHundredK);
        PRIZE_LIST.add(Prize.TwoHundredK);
        PRIZE_LIST.add(Prize.FiveHundredK);
        PRIZE_LIST.add(Prize.OneMillionK);
    }
    private String type;
    Prize(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Prize fromString(String text) {
        for (Prize prize : Prize.values()) {
            if (prize.type.equals(text)) {
                return prize;
            }
        }
        return null;
    }

    public static boolean isPrizeInList(Prize prize) {
        for (Prize p : PRIZE_LIST) {
            if (p == prize) {
                return true;
            }
        }
        return false;
    }
}
