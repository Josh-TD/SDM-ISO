package com.example.sdmisoback.model;

import jakarta.persistence.*;


@Entity
public class AttachType {
    @Id
    @Column(name="attachment_type", length = 50)
    private String attachmentType;

    @Column(name="description", nullable = false, length = 100)
    private String description;
    
    @Column(name="accplitation_category_type", nullable = false, length = 20)
    private String applicationCategoryType;

    // Constructors
    public AttachType(){
    }

    public AttachType(String description, String applicationCategoryType) {
        this.description = description;
        this.applicationCategoryType = applicationCategoryType;
    }

    public String getAttachmentType(){
        return attachmentType;
    }
    
    public String getDescription(){
        return description;
    }

    public String getApplicationCategoryType(){
        return applicationCategoryType;
    }
}
