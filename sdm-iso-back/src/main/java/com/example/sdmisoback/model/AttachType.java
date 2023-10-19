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

    // TODO: might have to put protection fpr attachmentType since it is a PK
    public AttachType(String attachmentType, String description, String applicationCategoryType) {
        this.attachmentType = attachmentType;
        this.description = description;
        this.applicationCategoryType = applicationCategoryType;
    }

    // gets and sets
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
