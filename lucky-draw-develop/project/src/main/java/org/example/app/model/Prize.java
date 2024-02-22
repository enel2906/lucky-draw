package org.example.app.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "prizes")
public class Prize {
    @Id
    private String id;
    private String type;
    private long quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Prize(String type, long quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public Prize(String id, String type, long quantity) {
        this.id = id;
        this.type = type;
        this.quantity = quantity;
    }
}
