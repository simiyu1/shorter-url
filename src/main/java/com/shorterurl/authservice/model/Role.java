package com.shorterurl.authservice.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.beans.factory.annotation.Value;

@Document(collection = "roles")
public class Role {

    @MongoId
    private String id;

    private RoleName name;

    public enum RoleName {
        ROLE_ADMIN,
        ROLE_USER
    }

    @PersistenceConstructor
    public Role(@Value("#root.roleName") RoleName roleName) {
        this.name = roleName;
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

