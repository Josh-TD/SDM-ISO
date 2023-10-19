package com.example.sdmisoback.model;

import jakarta.persistence.*;

@Entity
public class ProposalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="proposal_id")
    private int proposalId;

    @Column(name="proposal_label", length = 104)
    private String proposalLabel;
    
    @ManyToOne
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "fk_proj_info"))
    private ProjInfo projInfo;

    @ManyToOne
    @JoinColumn(name = "project_type", foreignKey = @ForeignKey(name = "fk_proj_type"))
    private ProjType projType;

    @ManyToOne
    @JoinColumn(name = "resource_id", foreignKey = @ForeignKey(name = "fk_res_info"))
    private ResInfo resInfo;

    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_cust_info"))
    private CustInfo custInfo; 

    @ManyToOne
    @JoinColumn(name = "auction_id", foreignKey = @ForeignKey(name = "fk_auc_info"))
    private AucInfo auctionInfo;

    @ManyToOne
    @JoinColumn(name = "period_id", foreignKey = @ForeignKey(name = "fk_period_info"))
    private PeriodInfo periodInfo;

    // constructors
    public ProposalInfo() {}

    public ProposalInfo(String proposalLabel, ProjInfo projInfo, ProjType projType, ResInfo resInfo, CustInfo custInfo,
            AucInfo auctionInfo, PeriodInfo periodInfo) {
        this.proposalLabel = proposalLabel;
        this.projInfo = projInfo;
        this.projType = projType;
        this.resInfo = resInfo;
        this.custInfo = custInfo;
        this.auctionInfo = auctionInfo;
        this.periodInfo = periodInfo;
    }

    // gets and sets
    public int getProposalId() {
        return proposalId;
    }

    public String getProposalLabel() {
        return proposalLabel;
    }

    public ProjInfo getProjInfo() {
        return projInfo;
    }

    public ProjType getProjType() {
        return projType;
    }

    public ResInfo getResInfo() {
        return resInfo;
    }

    public CustInfo getCustInfo() {
        return custInfo;
    }

    public AucInfo getAuctionInfo() {
        return auctionInfo;
    }

    public PeriodInfo getPeriodInfo() {
        return periodInfo;
    }
    

}
