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
public class ResType {
    @Id
    @Column(name="resource_type", length = 20)
    private String resourceType;

    @Column(name="resource_type_desc", nullable = false, length = 50)
    private String description;
}
