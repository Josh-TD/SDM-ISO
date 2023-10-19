package com.example.sdmisoback.model;
import java.util.Date;

import jakarta.persistence.*;

@Entity
public class AucInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="auction_id")
    private int auctionId;

    @ManyToOne
    @JoinColumn(name = "commitment_period_id", foreignKey = @ForeignKey(name = "fk_cp_info"))
    private PeriodInfo commitmentPeriodInfo;

    @ManyToOne
    @JoinColumn(name = "auction_period_id", foreignKey = @ForeignKey(name = "fk_auc_period"))
    private PeriodInfo auctionPeriodInfo;

    @Column(name="auction_begin_date")
    private Date auctionBeginDate;

    @Column(name="auction_end_date")
    private Date auctionEndDate;

    // TODO: see whats up with FK_AUC_TYPE in the diagram
    @Column(name="auction_type", length = 20)
    private String auctionType;

    // constructors
    public AucInfo() {}

    public AucInfo(PeriodInfo commitmentPeriodInfo, PeriodInfo auctionPeriodInfo, Date auctionBeginDate,
            Date auctionEndDate, String auctionType) {
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

    public Date getAuctionBeginDate() {
        return auctionBeginDate;
    }

    public Date getAuctionEndDate() {
        return auctionEndDate;
    }

    public String getAuctionType() {
        return auctionType;
    }
}