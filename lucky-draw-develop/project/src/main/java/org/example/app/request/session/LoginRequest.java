package org.example.app.request.session;

import org.example.app.exception.BusinessException;
import org.example.app.request.RequestData;
import static org.example.app.constant.ExceptionCode.*;
public class LoginRequest extends RequestData {
    private String userId;
    private String ipLogin;

    public LoginRequest() {

    }

    public LoginRequest(String userId, String ipLogin) {
        this.userId = userId;
        this.ipLogin = ipLogin;
    }
    public LoginRequest(String userId, String ipLogin, String aipName){
        setApiName(aipName);
        this.userId = userId;
        this.ipLogin = ipLogin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpLogin() {
        return ipLogin;
    }

    public void setIpLogin(String ipLogin) {
        this.ipLogin = ipLogin;
    }
    public void checkValidation(){
        if(userId == null || ipLogin == null || getApiName() == null){
            throw new BusinessException(REQUEST.getCode(), "Wrong data input!");
        }
    }
}
