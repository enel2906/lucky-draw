package org.example.app.response.award;

import org.example.app.response.ResponseData;
import static org.example.app.constant.ExceptionCode.*;

public class AcceptRollResponse extends ResponseData {
    private String award;

    public AcceptRollResponse(int code, String message) {
        super(code, message);
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public AcceptRollResponse(String award) {
        super(SUCCESS.getCode(), "Thanks for join in our lucky draw game!");
        this.award = award;
    }
}
