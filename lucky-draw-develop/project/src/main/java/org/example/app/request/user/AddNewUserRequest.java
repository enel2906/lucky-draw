package org.example.app.request.user;

import org.example.app.exception.BusinessException;
import org.example.app.request.RequestData;
import static org.example.app.constant.ExceptionCode.*;

public class AddNewUserRequest extends RequestData {
    private String name;
    public AddNewUserRequest(String name) {
        this.name = name;
    }
    public AddNewUserRequest(String name, String apiName){
        setApiName(apiName);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddNewUserRequest() {
    }

    public void checkValidation(){
        if(name == null || getApiName() == null){
            throw new BusinessException(REQUEST.getCode(), "Wrong data input!");
        }
    }
}
