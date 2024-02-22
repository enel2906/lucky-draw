package org.example.app.constant;

public enum ExceptionCode {
    SUCCESS(0),
    UNKNOWN(1),
    INVALID(2),
    REQUEST(3);

    private int code;

    ExceptionCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
