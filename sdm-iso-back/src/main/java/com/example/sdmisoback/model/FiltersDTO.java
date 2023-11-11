package com.example.sdmisoback.model;

import java.time.LocalDateTime;

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
    public String fileType;

    // project
    public Integer projectId;
    public String projectName;
    public String projectType;

    // customer
    public Integer customerId;
    public String customerName;

    // resource
    public Integer resourceId;
    public String resourceName;
    public String resourceType;

    // auction
    public Integer auctionId;

    // proposal
    public Integer proposalId;
    public String proposalLabel;
    public FiltersDTO(
            PageRequest pr, String sortBy, boolean sortAsc, 
            Integer fileId, String fileName, String fileDescription, LocalDateTime createdSince, String fileType, 
            Integer projectId, String projectName, String projectType, 
            Integer customerId, String customerName, 
            Integer resourceId, String resourceName, String resourceType, 
            Integer auctionId, 
            Integer proposalId, String proposalLabel
            ) {
        this.pr = pr;
        this.sortBy = sortBy;
        this.sortAsc = sortAsc;
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileDescription = fileDescription;
        this.createdSince = createdSince;
        this.fileType = fileType;
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectType = projectType;
        this.customerId = customerId;
        this.customerName = customerName;
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.auctionId = auctionId;
        this.proposalId = proposalId;
        this.proposalLabel = proposalLabel;
    }
}
