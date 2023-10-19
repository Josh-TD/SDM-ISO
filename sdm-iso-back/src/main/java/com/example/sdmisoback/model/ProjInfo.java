package com.example.sdmisoback.model;

import jakarta.persistence.*;

@Entity
public class ProjInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private int projectId;

    @Column(name="proj_name", length = 100)
    private String projectName;

    // constructors 
    public ProjInfo() {}

    public ProjInfo(String projectName) {
        this.projectName = projectName;
    }

    // gets and sets
    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }
}
