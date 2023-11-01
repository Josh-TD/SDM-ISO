package com.example.sdmisoback.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AttachProposal {
    
    @EmbeddedId
    AttachProposalKey id;

    @ManyToOne
    @MapsId("attachmentId")
    @JoinColumn(name = "attachment_id")
    private AttachmentFile attachmentFile;

    @ManyToOne
    @MapsId("proposalId")
    @JoinColumn(name = "proposal_id")
    private ProposalInfo proposalInfo;

    @ManyToOne
    @JoinColumn(name="attachment_type", nullable = false, foreignKey = @ForeignKey(name = "fk_attach_type"))
    private AttachType attachmentType;
}
