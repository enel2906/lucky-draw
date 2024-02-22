package org.example.app.exception;

public class BusinessException extends RuntimeException{

    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BusinessException(){
        super();
    }
    public BusinessException(int code, String message){
        this.code = code;
        this.message = message;
    }
}
