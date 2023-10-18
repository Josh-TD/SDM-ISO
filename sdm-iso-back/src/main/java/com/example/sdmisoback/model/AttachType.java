package com.example.sdmisoback.model;

import jakarta.persistence.*;


@Entity
public class AttachType {
    @Id
    @Column(length = 50)
    private String attachmentType;

    @Column(nullable = false, length = 100)
    private String description;
    
    @Column(nullable = false, length = 20)
    private String applicationCategoryType;

    // Constructors
    public AttachType(){
    }
    
    public AttachType(String attachmentType, String description, String applicationCategoryType) {
        this.attachmentType = attachmentType;
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
