package com.example.sdmisoback.repository;

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


public class CustomFiltersRepoImpl implements CustomFiltersRepo{

    private final EntityManager em;
 
    private final CriteriaBuilderFactory cbf;
 
    private final EntityViewManager evm;

    public CustomFiltersRepoImpl(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
    }

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

        if(f.propPeriodId != null)
            cb.where(proposal + ".periodInfo.periodId").eq(f.propPeriodId);

        if(f.propPeriodDesc != null)
            cb.where(proposal + ".periodInfo.description").like(false).value(f.propPeriodDesc + "%").noEscape();

        if(f.propPeriodBeginDate != null)
            cb.where(proposal + ".periodInfo.beginDate").eq(f.propPeriodBeginDate);

        if(f.propPeriodEndDate != null)
            cb.where(proposal + ".periodInfo.endDate").eq(f.propPeriodEndDate);

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

        // commitment period filters
        if(f.commitPeriodId != null)
            cb.where(proposal + ".auctionInfo.commitmentPeriodInfo.periodId").eq(f.commitPeriodId);

        if(f.commitPeriodTypes != null)
            cb.where(proposal + ".auctionInfo.commitmentPeriodInfo.periodType").in(f.commitPeriodTypes);

        if(f.commitPeriodDesc != null)
            cb.where(proposal + ".auctionInfo.commitmentPeriodInfo.description").like(false).value(f.commitPeriodDesc + "%").noEscape();

        if(f.commitPeriodBeginDate != null)
            cb.where(proposal + ".auctionInfo.commitmentPeriodInfo.beginDate").eq(f.commitPeriodBeginDate);

        if(f.commitPeriodEndDate != null)
            cb.where(proposal + ".auctionInfo.commitmentPeriodInfo.endDate").eq(f.commitPeriodEndDate);

        
        // auction period filters
        if(f.aucPeriodId != null)
            cb.where(proposal + ".auctionInfo.auctionPeriodInfo.periodId").eq(f.aucPeriodId);

        if(f.aucPeriodTypes != null)
            cb.where(proposal + ".auctionInfo.auctionPeriodInfo.periodType").in(f.aucPeriodTypes);

        if(f.aucPeriodDesc != null)
            cb.where(proposal + ".auctionInfo.auctionPeriodInfo.description").like(false).value(f.aucPeriodDesc + "%").noEscape();

        if(f.aucPeriodBeginDate != null)
            cb.where(proposal + ".auctionInfo.auctionPeriodInfo.beginDate").eq(f.aucPeriodBeginDate);

        if(f.aucPeriodEndDate != null)
            cb.where(proposal + ".auctionInfo.auctionPeriodInfo.endDate").eq(f.aucPeriodEndDate);

        
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
}
