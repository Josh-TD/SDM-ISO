package com.example.sdmisoback.model;
import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class AucInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="auction_id")
    private int auctionId;

    @ManyToOne
    @JoinColumn(name = "commitment_period_id", nullable = false, foreignKey = @ForeignKey(name = "fk_cp_info"))
    private PeriodInfo commitmentPeriodInfo;

    @ManyToOne
    @JoinColumn(name = "auction_period_id", nullable = false, foreignKey = @ForeignKey(name = "fk_auc_period"))
    private PeriodInfo auctionPeriodInfo;

    @Column(name="auction_begin_date", nullable = false)
    private LocalDateTime auctionBeginDate;

    @Column(name="auction_end_date", nullable = false)
    private LocalDateTime auctionEndDate;

    @ManyToOne
    @JoinColumn(name="auction_type", nullable = false, foreignKey = @ForeignKey(name = "fk_auction_type"))
    private AucType auctionType;
}