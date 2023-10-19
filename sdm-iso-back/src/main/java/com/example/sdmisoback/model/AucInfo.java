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
    private PeriodInfo AuctionPeriodInfo;

    @Column(name="auction_begin_date")
    private Date auctionBeginDate;

    @Column(name="auction_end_date")
    private Date auctionEndDate;

    // TODO: see whats up with FK_AUC_TYPE in the diagram
    @Column(name="auction_type", length = 20)
    private String auctionType;
}