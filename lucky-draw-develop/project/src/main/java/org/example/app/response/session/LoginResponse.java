package org.example.app.response.session;

import org.example.app.response.ResponseData;
import static org.example.app.constant.ExceptionCode.*;
public class LoginResponse extends ResponseData {
    private String token;

    public LoginResponse(int code, String message) {
        super(code, message);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponse(String token) {
        super(SUCCESS.getCode(), "Login successfully!");
        this.token = token;
    }
}
