package com.example.sdmisoback.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProposalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="proposal_id")
    private int proposalId;

    @Column(name="proposal_label", length = 104)
    private String proposalLabel;

    @OneToMany(mappedBy = "proposalInfo")
    private Set<AttachProposal> attachProposals;
    
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false, foreignKey = @ForeignKey(name = "fk_proj_info"))
    private ProjInfo projInfo;

    @ManyToOne
    @JoinColumn(name = "project_type", foreignKey = @ForeignKey(name = "fk_proj_type"))
    private ProjType projType;

    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false, foreignKey = @ForeignKey(name = "fk_res_info"))
    private ResInfo resInfo;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_cust_info"))
    private CustInfo custInfo; 

    @ManyToOne
    @JoinColumn(name = "auction_id", nullable = false, foreignKey = @ForeignKey(name = "fk_auc_info"))
    private AucInfo auctionInfo;

    @ManyToOne
    @JoinColumn(name = "period_id", nullable = false, foreignKey = @ForeignKey(name = "fk_period_info"))
    private PeriodInfo periodInfo;
}
