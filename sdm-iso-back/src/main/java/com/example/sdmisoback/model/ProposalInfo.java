package com.example.sdmisoback.model;

import jakarta.persistence.*;

@Entity
public class ProposalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ProposalId;

    @Column(name="proposal_label", length = 104)
    private String proposalLabel;

    @ManyToOne
    @JoinColumn(name = "auction_id", foreignKey = @ForeignKey(name = "fk_auc_info"))
    private AucInfo auctionInfo;

    @ManyToOne
    @JoinColumn(name = "period_id", foreignKey = @ForeignKey(name = "fk_period_info"))
    private PeriodInfo periodInfo;

    public ProposalInfo(){
    }
}
