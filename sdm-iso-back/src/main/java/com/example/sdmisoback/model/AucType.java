package com.example.sdmisoback.model;

import jakarta.persistence.*;

@Entity
public class AucType {
    @Id
    @Column(name="auction_type", length = 20)
    private String auctionType;

    @Column(name="description", nullable = false, length = 50)
    private String description;

    // constructors
    public AucType() {
    }
    
    // TODO: might have to put protection fpr auctionType since it is a PK
    public AucType(String auctionType, String description) {
        this.auctionType = auctionType;
        this.description = description;
    }

    // gets and sets
    public String getAuctionType() {
        return auctionType;
    }

    public String getDescription() {
        return description;
    }
}
