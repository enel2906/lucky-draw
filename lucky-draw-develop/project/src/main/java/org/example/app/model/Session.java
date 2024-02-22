package org.example.app.model;

import org.example.app.util.Util;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document(collection = "sessions")
public class Session {
    @Id
    @Field("_id")
    private String id;
    private String token;
    private String userId;
    private String ip;
    public String createToken(){
        return Util.getRandomString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Session() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public Session(String token, String userId, String ip) {
        this.token = token;
        this.userId = userId;
        this.ip = ip;
    }

    public Session(String userId, String ip) {
        this.token = createToken();
        this.userId = userId;
        this.ip = ip;
    }
}
