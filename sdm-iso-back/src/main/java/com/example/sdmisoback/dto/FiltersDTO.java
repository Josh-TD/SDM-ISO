package com.example.sdmisoback.dto;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;


import org.springframework.data.domain.PageRequest;

import lombok.Data;

@Data
public class FiltersDTO {
    // required
    public PageRequest pr;
    public String sortBy;
    public boolean sortAsc;
    
    // file
    public Integer fileId;
    public String fileName;
    public String fileDescription;
    public LocalDateTime createdSince;
    public List<String> fileTypes;

    // attach proposal
    public List<String> attachTypes;

    // proposal
    public Integer proposalId;
    public String proposalLabel;
    public Integer propPeriodId;
    public List<String> propPeriodTypes;
    public String propPeriodDesc;
    public LocalDate propPeriodBeginDate;
    public LocalDate propPeriodEndDate;

    // project
    public Integer projectId;
    public String projectName;
    public List<String> projectTypes;

    // customer
    public Integer customerId;
    public String customerName;

    // resource
    public Integer resourceId;
    public String resourceName;
    public List<String> resourceTypes;

    // auction
    public Integer auctionId;
    public List<String> auctionTypes;
    public LocalDate aucBeginDate;
    public LocalDate aucEndDate;

    // commitment period
    public Integer commitPeriodId;
    public List<String> commitPeriodTypes;
    public String commitPeriodDesc;
    public LocalDate commitPeriodBeginDate;
    public LocalDate commitPeriodEndDate;

    // auction period
    public Integer aucPeriodId;
    public List<String> aucPeriodTypes;
    public String aucPeriodDesc;
    public LocalDate aucPeriodBeginDate;
    public LocalDate aucPeriodEndDate;

    // cons
    public FiltersDTO(PageRequest pr, String sortBy, boolean sortAsc, Integer fileId, String fileName,
            String fileDescription, LocalDateTime createdSince, List<String> fileTypes, List<String> attachTypes,
            Integer proposalId, String proposalLabel, Integer propPeriodId, List<String> propPeriodTypes,
            String propPeriodDesc, LocalDate propPeriodBeginDate, LocalDate propPeriodEndDate,
            Integer projectId, String projectName, List<String> projectTypes, Integer customerId, String customerName,
            Integer resourceId, String resourceName, List<String> resourceTypes, Integer auctionId,
            List<String> auctionTypes, LocalDate aucBeginDate, LocalDate aucEndDate, Integer commitPeriodId,
            List<String> commitPeriodTypes, String commitPeriodDesc, LocalDate commitPeriodBeginDate,
            LocalDate commitPeriodEndDate, Integer aucPeriodId, List<String> aucPeriodTypes, String aucPeriodDesc,
            LocalDate aucPeriodBeginDate, LocalDate aucPeriodEndDate) {
        this.pr = pr;
        this.sortBy = sortBy;
        this.sortAsc = sortAsc;
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileDescription = fileDescription;
        this.createdSince = createdSince;
        this.fileTypes = fileTypes;
        this.attachTypes = attachTypes;
        this.proposalId = proposalId;
        this.proposalLabel = proposalLabel;
        this.propPeriodId = propPeriodId;
        this.propPeriodTypes = propPeriodTypes;
        this.propPeriodDesc = propPeriodDesc;
        this.propPeriodBeginDate = propPeriodBeginDate;
        this.propPeriodEndDate = propPeriodEndDate;
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectTypes = projectTypes;
        this.customerId = customerId;
        this.customerName = customerName;
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.resourceTypes = resourceTypes;
        this.auctionId = auctionId;
        this.auctionTypes = auctionTypes;
        this.aucBeginDate = aucBeginDate;
        this.aucEndDate = aucEndDate;
        this.commitPeriodId = commitPeriodId;
        this.commitPeriodTypes = commitPeriodTypes;
        this.commitPeriodDesc = commitPeriodDesc;
        this.commitPeriodBeginDate = commitPeriodBeginDate;
        this.commitPeriodEndDate = commitPeriodEndDate;
        this.aucPeriodId = aucPeriodId;
        this.aucPeriodTypes = aucPeriodTypes;
        this.aucPeriodDesc = aucPeriodDesc;
        this.aucPeriodBeginDate = aucPeriodBeginDate;
        this.aucPeriodEndDate = aucPeriodEndDate;
    }


}
