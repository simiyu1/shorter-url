package com.shorterurl.authservice.model;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User {

    @MongoId
    private String id;

    private String username;
    private String password;

    private Set<Role> roles = new HashSet<>();

    // Getters and Setters
}