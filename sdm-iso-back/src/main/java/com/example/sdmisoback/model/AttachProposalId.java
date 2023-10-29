package com.example.sdmisoback.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

// Class to make a composite key for AttachProposal
@Embeddable
public class AttachProposalId implements Serializable{
    @ManyToOne
    @JoinColumn(name="proposal_id", foreignKey = @ForeignKey(name = "fk_proposal_info"))
    private ProposalInfo proposalId;

    @ManyToOne
    @JoinColumn(name="attachment_id", foreignKey = @ForeignKey(name = "fk_attach_file"))
    private AttachmentFile attachmentFileId;

    public AttachProposalId(){
    }

    public AttachProposalId(ProposalInfo proposalId, AttachmentFile attachmentFileId){
        this.proposalId = proposalId;
        this.attachmentFileId = attachmentFileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttachProposalId that = (AttachProposalId) o;
        return Objects.equals(proposalId, that.proposalId) &&
               Objects.equals(attachmentFileId, that.attachmentFileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proposalId, attachmentFileId);
    }
}
