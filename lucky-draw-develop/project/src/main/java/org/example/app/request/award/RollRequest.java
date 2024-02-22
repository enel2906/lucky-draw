package org.example.app.request.award;

import org.example.app.request.RequestData;

public class RollRequest extends RequestData {
    public RollRequest() {
    }

    public RollRequest(String token) {
        super(token);
    }

    public RollRequest(String token, String apiName) {
        super(token, apiName);
    }
}
