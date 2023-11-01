package com.example.sdmisoback.model;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class ProjInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private int projectId;

    @Column(name="project_name", length = 100)
    private String projectName;
}
