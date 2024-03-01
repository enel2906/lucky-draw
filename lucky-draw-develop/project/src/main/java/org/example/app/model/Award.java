package org.example.app.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "awards")
public class Award {
    @Id
    private String id;
    private String userId;
    private String type;

    private int numTurns;

    public int getNumTurns() {
        return numTurns;
    }

    public void setNumTurns(int numTurns) {
        this.numTurns = numTurns;
    }

    public Award(String id, String userId, String type, int numTurns) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.numTurns = numTurns;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Award(String userId, String type) {
        this.userId = userId;
        this.type = type;
    }

    public Award(String id, String userId, String type) {
        this.id = id;
        this.userId = userId;
        this.type = type;
    }
}
