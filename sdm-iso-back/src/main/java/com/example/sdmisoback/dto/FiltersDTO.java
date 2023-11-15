package com.example.sdmisoback.dto;

import java.time.LocalDateTime;
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

    // period
    public Integer periodId;
    public String periodDesc;

    
    public FiltersDTO(PageRequest pr, String sortBy, boolean sortAsc, Integer fileId, String fileName,
            String fileDescription, LocalDateTime createdSince, List<String> fileTypes, List<String> attachTypes,
            Integer proposalId, String proposalLabel, Integer projectId, String projectName, List<String> projectTypes,
            Integer customerId, String customerName, Integer resourceId, String resourceName,
            List<String> resourceTypes, Integer auctionId, Integer periodId, String periodDesc) {
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
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectTypes = projectTypes;
        this.customerId = customerId;
        this.customerName = customerName;
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.resourceTypes = resourceTypes;
        this.auctionId = auctionId;
        this.periodId = periodId;
        this.periodDesc = periodDesc;
    }
    
    
}
