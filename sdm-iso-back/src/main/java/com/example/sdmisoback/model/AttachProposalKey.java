package com.example.sdmisoback.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class AttachProposalKey implements Serializable{
    
    @Column(name = "attachment_id")
    private Integer attachmentId;

    @Column(name = "proposal_id")
    private Integer proposalId;

}
