package org.example.app.response.award;

import org.example.app.response.ResponseData;
import static org.example.app.constant.ExceptionCode.*;

public class RollResponse extends ResponseData {
    private String type;
    public RollResponse(String type) {
        super(SUCCESS.getCode(), "Roll Successfully!");
        this.type = type;
    }

    public RollResponse(int code, String message) {
        super(code, message);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
