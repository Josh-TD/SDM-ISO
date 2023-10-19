package com.example.sdmisoback.model;

import jakarta.persistence.*;

public class ProjInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private int projectId;

    @Column(name="proj_name", length = 100)
    private String projectName;

    public ProjInfo(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }
}
