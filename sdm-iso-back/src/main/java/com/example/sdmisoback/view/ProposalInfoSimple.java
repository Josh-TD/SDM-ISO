package com.example.sdmisoback.view;


import java.util.Set;

import com.blazebit.persistence.view.*;
import com.example.sdmisoback.model.*;

// Acts like a Data-transfer-object(DTO) so that we only send the 
// most necessary information over to the front end
// class is temporary and the real FileDTO will include more info
@EntityView(ProposalInfo.class)
public interface ProposalInfoSimple {
    
    @IdMapping
    public int getProposalId();

    @Mapping("projInfo.projectId")
    public int getProjectId();

    @Mapping("projInfo.projectName")
    public String getProjectName();

    @Mapping("custInfo.customerId")
    public int getCustomerId();

    @Mapping("custInfo.customerName")
    public String getCustomerName();

    @Mapping("attachProposals.attachmentFile.attachmentId")
    public int getAttachmentFile();

    @Mapping("attachProposals.attachmentFile.fileName")
    public String getFileName();

    @Mapping("attachProposals.attachmentFile.description")
    public String getFileDescription();
}
