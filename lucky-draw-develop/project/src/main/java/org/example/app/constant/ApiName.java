package org.example.app.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ApiName {
    ACCEPT_API_NAME("accept"),
    ROLL_API_NAME("roll"),
    LOGIN_API_NAME("log-in"),
    ADD_NEW_USER("add-new-user");

    public String getName() {
        return name;
    }

    public static final List<ApiName> UNAUTHEN_API = Arrays.asList(LOGIN_API_NAME);
    public final String name;


    ApiName(String name) {
        this.name = name;
    }

    public static ApiName fromString(String name) {
        for (ApiName apiName : values()) {
            if (apiName.name.equals(name)) {
                return apiName;
            }
        }
        return null;
    }

}
