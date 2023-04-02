package com.shorterurl.authservice.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "roles")
public class Role {

    @MongoId
    private String id;

    private RoleName name;

    public enum RoleName {
        ROLE_ADMIN,
        ROLE_USER
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}

