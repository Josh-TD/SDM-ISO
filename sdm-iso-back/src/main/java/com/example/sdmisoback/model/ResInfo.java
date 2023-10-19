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

    @ManyToOne
    @JoinColumn(name="resource_type", foreignKey = @ForeignKey(name = "fk_resource_type"))
    private ResType resourceType;
    
    // constructers
    public ResInfo() {}

    public ResInfo(String resourceName, ResType resourceType) {
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
        return resourceType.getResourceType();
    }


}
