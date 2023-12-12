package com.example.sdmisoback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachType {
    @Id
    @Column(name="attachment_type", length = 50)
    private String attachmentType;

    @Column(name="description", nullable = false, length = 100)
    private String description;
    
    @Column(name="application_category_type", nullable = false, length = 20)
    private String applicationCategoryType;
}
