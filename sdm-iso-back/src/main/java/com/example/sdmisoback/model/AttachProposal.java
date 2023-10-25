package com.example.sdmisoback.model;

import java.io.Serializable;

import jakarta.persistence.*;


@Entity
@IdClass(AttachProposalId.class)
public class AttachProposal implements Serializable{

    // Foreign key from ProposalInfo
    @Id
    @Column(name="proposal_id")
    private int proposalId;

    // Foreign key from AttachmentFile
    @Id
    @Column(name="attachment_id")
    private int attachmentFileId;

    // Foreign key from AttachType
    @ManyToOne
    @JoinColumn(name="attachment_type", nullable = false, foreignKey = @ForeignKey(name = "fk_attach_type"))
    private AttachType attachmentType;

    // constructors 
    public AttachProposal() {
    }

    public AttachProposal( int attachmentFileId, AttachType attachmentType) {
        this.attachmentFileId = attachmentFileId;
        this.attachmentType = attachmentType;
    }

    // gets and sets
    public int getProposalId() {
        return proposalId;
    }

    public int getAttachmentFileId() {
        return attachmentFileId;
    }
    
    public String getAttachmentType() {
        return attachmentType.getAttachmentType();
    }
}


