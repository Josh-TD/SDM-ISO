package com.example.sdmisoback.model;

import jakarta.persistence.*;

@Entity
public class ProjType {
    @Id
    @Column(name="project_type", length = 50)
    private String projectType;

    @Column(name="project_type_desc", nullable = false, length = 100)
    private String projTypeDesc;

    // constructors
    public ProjType() {}

    // TODO: might have to put protection fpr attachmentType since it is a PK
    public ProjType(String projectType, String projTypeDesc) {
        this.projectType = projectType;
        this.projTypeDesc = projTypeDesc;
    }

    // gets and sets
    public String getProjectType() {
        return projectType;
    }

    public String getProjTypeDesc() {
        return projTypeDesc;
    }
}
