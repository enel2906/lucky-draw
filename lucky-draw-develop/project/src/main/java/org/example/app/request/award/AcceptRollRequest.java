package org.example.app.request.award;

import org.example.app.request.RequestData;

public class AcceptRollRequest extends RequestData {
    public AcceptRollRequest() {
    }

    public AcceptRollRequest(String token) {
        super(token);
    }

    public AcceptRollRequest(String apiName, String token) {
        super(apiName, token);
    }
}
