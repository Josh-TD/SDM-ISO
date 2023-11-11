package com.example.sdmisoback.dto;

import java.time.LocalDateTime;

import com.blazebit.persistence.view.*;
import com.example.sdmisoback.model.*;

// Acts like a Data-transfer-object(DTO) so that we only send the 
// most necessary information over to the front end
// class is temporary and the real FileDTO will include more info
@EntityView(AttachmentFile.class)
public interface AttachmentFileView {

    @IdMapping
    public int getAttachmentId();

    @Mapping("fileName")
    public String getFileName();

    @Mapping("description")
    public String getFileDescription();

    @Mapping("createDate")
    public LocalDateTime getFileCreateDate();

    // TODO: probably do not need proposalId, was using it previously for testing
    @Mapping("attachProposals.proposalInfo.proposalId")
    public int getProposalId();

    @Mapping("attachProposals.proposalInfo.projInfo.projectId")
    public int getProjectId();

    @Mapping("attachProposals.proposalInfo.projInfo.projectName")
    public String getProjectName();

    @Mapping("attachProposals.proposalInfo.custInfo.customerId")
    public int getCustomerId();

    @Mapping("attachProposals.proposalInfo.custInfo.customerName")
    public String getCustomerName();
    
    
}
