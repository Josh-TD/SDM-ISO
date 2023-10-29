package com.example.sdmisoback.model;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
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

    // constructors
    public AucInfo() {}

    public AucInfo(PeriodInfo commitmentPeriodInfo, PeriodInfo auctionPeriodInfo, LocalDateTime auctionBeginDate,
            LocalDateTime auctionEndDate, AucType auctionType) {
        this.commitmentPeriodInfo = commitmentPeriodInfo;
        this.auctionPeriodInfo = auctionPeriodInfo;
        this.auctionBeginDate = auctionBeginDate;
        this.auctionEndDate = auctionEndDate;
        this.auctionType = auctionType;
    }

    // gets and sets
    public int getAuctionId() {
        return auctionId;
    }

    public PeriodInfo getCommitmentPeriodInfo() {
        return commitmentPeriodInfo;
    }

    public PeriodInfo getAuctionPeriodInfo() {
        return auctionPeriodInfo;
    }

    public LocalDateTime getAuctionBeginDate() {
        return auctionBeginDate;
    }

    public LocalDateTime getAuctionEndDate() {
        return auctionEndDate;
    }

    public String getAuctionType() {
        return auctionType.getAuctionType();
    }
}