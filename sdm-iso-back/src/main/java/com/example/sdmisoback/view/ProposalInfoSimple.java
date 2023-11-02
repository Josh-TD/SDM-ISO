package com.example.sdmisoback.view;

import com.blazebit.persistence.view.*;
import com.example.sdmisoback.model.*;

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
}
