package com.example.sdmisoback.model;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class AucType {
    @Id
    @Column(name="auction_type", length = 20)
    private String auctionType;

    @Column(name="description", nullable = false, length = 50)
    private String description;
}
