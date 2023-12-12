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
public class ResInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="resource_id")
    private int resourceId;

    @Column(name="resource_name", length = 100)
    private String resourceName;

    @ManyToOne
    @JoinColumn(name="resource_type", foreignKey = @ForeignKey(name = "fk_resource_type"))
    private ResType resourceType;
}
