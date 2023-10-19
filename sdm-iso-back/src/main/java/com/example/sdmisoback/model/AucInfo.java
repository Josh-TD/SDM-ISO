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

    @ManyToOne
    @JoinColumn(name="auction_type", foreignKey = @ForeignKey(name = "fk_auction_type"))
    private AucType auctionType;

    // constructors
    public AucInfo() {}

    public AucInfo(PeriodInfo commitmentPeriodInfo, PeriodInfo auctionPeriodInfo, Date auctionBeginDate,
            Date auctionEndDate, AucType auctionType) {
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
        return auctionType.getAuctionType();
    }
}