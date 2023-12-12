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
public class AucType {
    @Id
    @Column(name="auction_type", length = 20)
    private String auctionType;

    @Column(name="description", nullable = false, length = 50)
    private String description;
}
