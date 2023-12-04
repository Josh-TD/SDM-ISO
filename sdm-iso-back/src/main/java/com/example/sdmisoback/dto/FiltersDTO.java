package com.example.sdmisoback.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
