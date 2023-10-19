package com.example.sdmisoback.model;

import jakarta.persistence.*;

@Entity
public class ResType {
    @Id
    @Column(name="resource_type", length = 20)
    private String resourceType;

    @Column(name="description", nullable = false, length = 50)
    private String description;

    // constructors 
    public ResType() {
    }

    // TODO: might have to put protection fpr resourceType since it is a PK
    public ResType(String resourceType, String description) {
        this.resourceType = resourceType;
        this.description = description;
    }

    // gets and sets
    public String getResourceType() {
        return resourceType;
    }

    public String getDescription() {
        return description;
    }
}
