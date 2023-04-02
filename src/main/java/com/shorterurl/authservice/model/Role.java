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

    // Getters and Setters
}

