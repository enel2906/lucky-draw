package org.example.app.response.user;

import org.example.app.response.ResponseData;
import static org.example.app.constant.ExceptionCode.*;

public class AddNewUserResponse extends ResponseData {
    private String userId;

    public AddNewUserResponse(String userId) {
        super(SUCCESS.getCode(), "Add new user Successfully!");
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AddNewUserResponse(int code, String message) {
        super(code, message);
    }
}
