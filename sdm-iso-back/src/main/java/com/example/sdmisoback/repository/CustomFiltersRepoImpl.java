package com.example.sdmisoback.repository;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.PaginatedCriteriaBuilder;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.example.sdmisoback.dto.AttachmentFileView;
import com.example.sdmisoback.dto.FiltersDTO;
import com.example.sdmisoback.model.AttachmentFile;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CustomFiltersRepoImpl implements CustomFiltersRepo{

    private final EntityManager em;
 
    private final CriteriaBuilderFactory cbf;
 
    private final EntityViewManager evm;

    @Override
    public Page<AttachmentFileView> filterAttachments(FiltersDTO f){
        // shortcut for finding proposalInfo
        String proposal = "attachProposals.proposalInfo";
        
        // create base query here (create = from)
        CriteriaBuilder<AttachmentFile> cb = cbf.create(em, AttachmentFile.class)
                                                .orderBy(f.sortBy, f.sortAsc)
                                                .orderByAsc("attachmentId"); 

        // add predicates/WHERE clauses below

        // attachmentFile filters
        if(f.fileName != null)
            cb.where("fileName").like(false).value(f.fileName+'%').noEscape();
        
        if(f.fileId != null)
            cb.where("attachmentId").eq(f.fileId);

        if(f.fileDescription != null)
            cb.where("description").like(false).value('%'+ f.fileDescription + '%').noEscape();

        if(f.createdSince != null)
            cb.where("createDate").ge(f.createdSince);

        if(f.fileTypes != null){
            StringBuilder sb = new StringBuilder();
            int size = f.fileTypes.size();
            for (int i = 0; i < size - 1; i++) {
                sb.append("fileName LIKE '%." + f.fileTypes.get(i) + "' OR ");
            }
            sb.append("fileName LIKE '%." + f.fileTypes.get(size-1) + "'");
            cb.whereExpression(sb.toString());
        }

        // attach proposal filters
        if(f.attachTypes != null)
            cb.where("attachProposals.attachmentType.attachmentType").in(f.attachTypes);
        
        // proposal filters
        if(f.proposalId != null)
            cb.where(proposal + ".proposalId").eq(f.proposalId);

        if(f.proposalLabel != null)
            cb.where(proposal + ".proposalLabel").like(false).value('%'+f.proposalLabel + '%').noEscape();

        // project filters
        if(f.projectId != null)
            cb.where(proposal + ".projInfo.projectId").eq(f.projectId);
        
        if(f.projectName != null)
            cb.where(proposal + ".projInfo.projectName").like(false).value(f.projectName+'%').noEscape();
        
        if(f.projectTypes != null)
            cb.where(proposal + ".projType.projectType").in(f.projectTypes);

        // customer filters
        if(f.customerId != null)
            cb.where(proposal + ".custInfo.customerId").eq(f.customerId);

        if(f.customerName != null)
            cb.where(proposal + ".custInfo.customerName").like(false).value(f.customerName+'%').noEscape();

        // resource filters
        if(f.resourceId != null)
            cb.where(proposal + ".resInfo.resourceId").eq(f.resourceId);
        
        if(f.resourceName != null)
            cb.where(proposal + ".resInfo.resourceName").like(false).value(f.resourceName+'%').noEscape();

        if(f.resourceTypes != null)
            cb.where(proposal + ".resInfo.resourceType.resourceType").in(f.resourceTypes);

        // auction filters
        if(f.auctionId != null)
            cb.where(proposal + ".auctionInfo.auctionId").eq(f.auctionId);

        // period filters
        if(f.periodId != null)
            cb.where(proposal + ".periodInfo.periodId").eq(f.periodId);
        
        if(f.periodDesc != null)
            cb.where(proposal + ".periodInfo.description").like(false).value(f.periodDesc+'%').noEscape();
        
        // add pagination here
        // first is creating the "setting" to apply pagination
        EntityViewSetting<AttachmentFileView, PaginatedCriteriaBuilder<AttachmentFileView>> setting = 
            EntityViewSetting.create(AttachmentFileView.class, f.pr.getPageNumber() * f.pr.getPageSize(), f.pr.getPageSize());

        // then apply the setting and execute the query
        PagedList<AttachmentFileView> pagedAttachments = evm.applySetting(setting, cb)
            .getResultList();
        
        // finally get the total count of the query and return
        int count = (int) pagedAttachments.getTotalSize();
        return new PageImpl<>(pagedAttachments, f.pr, count);
    }

    @Override 
    public List<AttachmentFileView> getRecentlyViewed(List<Integer> fileIds){
       CriteriaBuilder<AttachmentFile> cb = cbf.create(em, AttachmentFile.class)
                                                .where("attachmentId").in(fileIds)
                                                .setMaxResults(10)
                                                .orderByAsc("attachmentId");

        List<AttachmentFileView> fileViews = evm.applySetting(EntityViewSetting.create(AttachmentFileView.class), cb).getResultList();
                                                

        fileViews.sort(Comparator.comparingInt(file -> fileIds.indexOf(file.getAttachmentId())));
        Collections.reverse(fileViews);
        return fileViews;
    }
}
