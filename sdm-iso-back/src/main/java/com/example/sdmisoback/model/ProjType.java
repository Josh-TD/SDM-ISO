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
public class ProjType {
    @Id
    @Column(name="project_type", length = 50)
    private String projectType;

    @Column(name="project_type_desc", nullable = false, length = 100)
    private String projTypeDesc;
}
