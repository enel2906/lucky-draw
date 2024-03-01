package org.example.app.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document("users")
public class User {
    @Id
    @Field("_id")
    private String userId;
    private String name;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public User(String name, int numTurns) {
        this.name = name;
    }

    public User() {
    }

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
