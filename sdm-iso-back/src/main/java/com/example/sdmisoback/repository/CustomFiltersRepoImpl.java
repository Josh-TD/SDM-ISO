package com.example.sdmisoback.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.view.EntityViewManager;
import com.example.sdmisoback.dto.FileViewDTO;
import com.example.sdmisoback.dto.FiltersDTO;
import com.example.sdmisoback.model.AttachProposal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;


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
    public Page<FileViewDTO> filterAttachments(FiltersDTO f){        
        // create base query here, based from attach_proposal so that 
        // all file->proposal relationships are shown
        // it is our choice to show copies of the same file with different customer/project names
        CriteriaBuilder<Tuple> cb = cbf.create(em, Tuple.class)
            .from(AttachProposal.class, "ap")
            .leftJoin("ap.attachmentFile", "af")
            .leftJoin("ap.proposalInfo", "p")
            .leftJoin("p.custInfo", "c")
            .leftJoin("p.projInfo", "pj")
            .select("af.attachmentId")
            .select("af.fileName")
            .select("af.description")
            .select("af.createDate")
            .select("c.customerId")
            .select("c.customerName")
            .select("pj.projectId")
            .select("pj.projectName")
            .orderBy(f.sortBy, f.sortAsc)
            .orderByAsc("ap.id");

        // add predicates/WHERE clauses below

        // attachmentFile filters
        if(f.fileName != null)
            cb.where("af.fileName").like(false).value(f.fileName+'%').noEscape();
        
        if(f.fileId != null)
            cb.where("af.attachmentId").eq(f.fileId);

        if(f.fileDescription != null)
            cb.where("af.description").like(false).value('%'+ f.fileDescription + '%').noEscape();

        if(f.createdSince != null)
            cb.where("af.createDate").ge(f.createdSince);

        if(f.fileTypes != null){
            StringBuilder sb = new StringBuilder();
            int size = f.fileTypes.size();
            for (int i = 0; i < size - 1; i++) {
                sb.append("af.fileName LIKE '%." + f.fileTypes.get(i) + "' OR ");
            }
            sb.append("af.fileName LIKE '%." + f.fileTypes.get(size-1) + "'");
            cb.whereExpression(sb.toString());
        }

        // attach proposal filters
        if(f.attachTypes != null)
            cb.where("ap.attachmentType.attachmentType").in(f.attachTypes);
        
        // proposal filters
        if(f.proposalId != null)
            cb.where("p.proposalId").eq(f.proposalId);

        if(f.proposalLabel != null)
            cb.where("p.proposalLabel").like(false).value('%'+f.proposalLabel + '%').noEscape();

        if(f.propPeriodId != null)
            cb.where("p.periodInfo.periodId").eq(f.propPeriodId);

        if(f.propPeriodTypes != null)
            cb.where("p.periodInfo.periodType").in(f.propPeriodTypes);

        if(f.propPeriodDesc != null)
            cb.where("p.periodInfo.description").like(false).value(f.propPeriodDesc + "%").noEscape();

        if(f.propPeriodBeginDate != null)
            cb.where(proposal + ".periodInfo.beginDate").ge(f.propPeriodBeginDate);

        if(f.propPeriodEndDate != null)
            cb.where(proposal + ".periodInfo.endDate").le(f.propPeriodEndDate);

        // project filters
        if(f.projectId != null)
            cb.where("pj.projectId").eq(f.projectId);
        
        if(f.projectName != null)
            cb.where("pj.projectName").like(false).value(f.projectName+'%').noEscape();
        
        if(f.projectTypes != null)
            cb.where("p.projType.projectType").in(f.projectTypes);

        // customer filters
        if(f.customerId != null)
            cb.where("c.customerId").eq(f.customerId);

        if(f.customerName != null)
            cb.where("c.customerName").like(false).value(f.customerName+'%').noEscape();

        // resource filters
        if(f.resourceId != null)
            cb.where("p.resInfo.resourceId").eq(f.resourceId);
        
        if(f.resourceName != null)
            cb.where("p.resInfo.resourceName").like(false).value(f.resourceName+'%').noEscape();

        if(f.resourceTypes != null)
            cb.where("p.resInfo.resourceType.resourceType").in(f.resourceTypes);

        // auction filters
        if(f.auctionId != null)
            cb.where("p.auctionInfo.auctionId").eq(f.auctionId);

        if(f.auctionTypes != null)
            cb.where(proposal + ".auctionInfo.auctionType.auctionType").in(f.auctionTypes);

        if(f.aucBeginDate != null)
            cb.where(proposal + ".auctionInfo.auctionBeginDate").ge(f.aucBeginDate);

        if(f.aucEndDate != null)
            cb.where(proposal + ".auctionInfo.auctionEndDate").le(f.aucEndDate);

        // commitment period filters
        if(f.commitPeriodId != null)
            cb.where("p.auctionInfo.commitmentPeriodInfo.periodId").eq(f.commitPeriodId);

        if(f.commitPeriodTypes != null)
            cb.where("p.auctionInfo.commitmentPeriodInfo.periodType").in(f.commitPeriodTypes);

        if(f.commitPeriodDesc != null)
            cb.where("p.auctionInfo.commitmentPeriodInfo.description").like(false).value(f.commitPeriodDesc + "%").noEscape();

        if(f.commitPeriodBeginDate != null)
            cb.where(proposal + ".auctionInfo.commitmentPeriodInfo.beginDate").ge(f.commitPeriodBeginDate);

        if(f.commitPeriodEndDate != null)
            cb.where(proposal + ".auctionInfo.commitmentPeriodInfo.endDate").le(f.commitPeriodEndDate);

        // auction period filters
        if(f.aucPeriodId != null)
            cb.where("p.auctionInfo.auctionPeriodInfo.periodId").eq(f.aucPeriodId);

        if(f.aucPeriodTypes != null)
            cb.where("p.auctionInfo.auctionPeriodInfo.periodType").in(f.aucPeriodTypes);

        if(f.aucPeriodDesc != null)
            cb.where("p.auctionInfo.auctionPeriodInfo.description").like(false).value(f.aucPeriodDesc + "%").noEscape();

        if(f.aucPeriodBeginDate != null)
            cb.where(proposal + ".auctionInfo.auctionPeriodInfo.beginDate").ge(f.aucPeriodBeginDate);

        if(f.aucPeriodEndDate != null)
            cb.where(proposal + ".auctionInfo.auctionPeriodInfo.endDate").le(f.aucPeriodEndDate);
        
        // add pagination here

        // then apply the setting and execute the query
        PagedList<Tuple> pagedAttachments = cb.page(f.pr.getPageNumber() * f.pr.getPageSize(), f.pr.getPageSize()).getResultList();
        List<FileViewDTO> results = pagedAttachments.stream()
            .map(tuple -> new FileViewDTO(
                tuple.get(0, Integer.class),        // attachmentId
                tuple.get(1, String.class),         // fileName
                tuple.get(2, String.class),         // description
                tuple.get(3, LocalDateTime.class),  // createDate
                tuple.get(6, Integer.class),        // projectId
                tuple.get(7, String.class),         // projectName
                tuple.get(4, Integer.class),        // customerId
                tuple.get(5, String.class)          // customerName
            ))
            .collect(Collectors.toList());
        
        // finally get the total count of the query and return
        int count = (int) pagedAttachments.getTotalSize();
        return new PageImpl<>(results, f.pr, count);
    }
}