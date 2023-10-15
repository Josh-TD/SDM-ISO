package com.example.sdmisoback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    private String emailAddress;
    private String password;
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
