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

import com.example.sdmisoback.model.*;
import com.example.sdmisoback.view.AttachmentFileView;


public class CustomAttachmentFileRepoImpl implements CustomAttachmentFileRepo{

    private final EntityManager em;
 
    private final CriteriaBuilderFactory cbf;
 
    private final EntityViewManager evm;

    public CustomAttachmentFileRepoImpl(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
    }

    @Override
    public Page<AttachmentFileView> filterAttachments(PageRequest pr, Integer proposalId){
        // create base query here (create = from)
        CriteriaBuilder<AttachmentFile> cb = cbf.create(em, AttachmentFile.class)
                                                .orderByAsc("attachmentId"); // can apply sort by here

        // add predicates here
        if(proposalId != null){
            cb.where("attachProposals.proposalInfo.proposalId").eq(proposalId);
        }

        // add pagination here
        // first is creating the "setting" to apply pagination
        EntityViewSetting<AttachmentFileView, PaginatedCriteriaBuilder<AttachmentFileView>> setting = 
            EntityViewSetting.create(AttachmentFileView.class, pr.getPageNumber() * pr.getPageSize(), pr.getPageSize());

        // then apply the setting and execute the query
        PagedList<AttachmentFileView> pagedAttachments = evm.applySetting(setting, cb)
            .getResultList();
        
        // finally get the total count of the query and return
        int count = (int) pagedAttachments.getTotalSize();
        return new PageImpl<>(pagedAttachments, pr, count);
    }
}
