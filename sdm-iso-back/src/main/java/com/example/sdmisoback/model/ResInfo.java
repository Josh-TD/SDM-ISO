package com.example.sdmisoback.model;

import jakarta.persistence.*;

@Entity
public class ResInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="resource_id")
    private int resourceId;

    @Column(name="resource_name", length = 100)
    private String resourceName;

    // TODO: see whats up with FK_RES_TYPE in the diagram
    @Column(name="resource_type", length = 20)
    private String resourceType;
    
    // constructers
    public ResInfo() {}

    public ResInfo(String resourceName, String resourceType) {
        this.resourceName = resourceName;
        this.resourceType = resourceType;
    }

    // gets and sets
    public int getResourceId() {
        return resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }
}
