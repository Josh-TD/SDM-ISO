package com.example.sdmisoback.repository;

import jakarta.persistence.EntityManager;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.PaginatedCriteriaBuilder;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.example.sdmisoback.model.ProposalInfo;
import com.example.sdmisoback.view.ProposalInfoSimple;


public class CustomProposalRepoImpl implements CustomProposalRepo{

    private final EntityManager em;
 
    private final CriteriaBuilderFactory cbf;
 
    private final EntityViewManager evm;

    public CustomProposalRepoImpl(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
    }

    @Override
    public Page<ProposalInfoSimple> findProposalSimpleById(PageRequest pr, Integer proposalId){
        // create base query here
        CriteriaBuilder<ProposalInfo> cb = cbf.create(em, ProposalInfo.class)
                                              .orderByAsc("proposalId");

        // add predicates here
        if(proposalId != null){
            cb.where("proposalId").eq(proposalId);
        }

        // add pagination here
        // first is creating the "setting" to apply pagination
        EntityViewSetting<ProposalInfoSimple, PaginatedCriteriaBuilder<ProposalInfoSimple>> setting = 
            EntityViewSetting.create(ProposalInfoSimple.class, pr.getPageNumber() * pr.getPageSize(), pr.getPageSize());

        // then apply the setting and execute the query
        PagedList<ProposalInfoSimple> pagedProposals = evm.applySetting(setting, cb)
            .getResultList();
        
        // finally get the total count of the query and return
        int count = (int) pagedProposals.getTotalSize();
        return new PageImpl<>(pagedProposals, pr, count);
    }
}
