package org.example.app.constant;

public enum Prize {
    FiftyK("50K"),
    OneHundredK("100K"),
    TwoHundredK("200K"),
    FiveHundredK("500K"),
    OneMillionK("1000K");


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
}
