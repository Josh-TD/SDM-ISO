package com.example.sdmisoback.model;

import jakarta.persistence.*;

@Entity
public class ProposalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="proposal_id")
    private int ProposalId;

    @Column(name="proposal_label", length = 104)
    private String proposalLabel;
    
    @ManyToOne
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "fk_proj_info"))
    private ProjInfo projInfo;

    @ManyToOne
    @JoinColumn(name = "project_type", foreignKey = @ForeignKey(name = "fk_proj_type"))
    private ProjType projType;

    @ManyToOne
    @JoinColumn(name = "auction_id", foreignKey = @ForeignKey(name = "fk_auc_info"))
    private AucInfo auctionInfo;

    @ManyToOne
    @JoinColumn(name = "period_id", foreignKey = @ForeignKey(name = "fk_period_info"))
    private PeriodInfo periodInfo;

    public ProposalInfo(){
    }
}
