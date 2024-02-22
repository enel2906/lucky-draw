package org.example.app.constant;

public enum NumRoll {
    LuckyDrawTurns(2);
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    NumRoll(int amount) {
        this.amount = amount;
    }
}
