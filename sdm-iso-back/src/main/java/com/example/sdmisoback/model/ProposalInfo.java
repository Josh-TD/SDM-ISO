package com.example.sdmisoback.model;

import jakarta.persistence.*;

@Entity
public class ProposalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ProposalId;

    @Column(name="proposal_label", length = 104)
    private String proposalLabel;

    

    public ProposalInfo(){
    }
}
