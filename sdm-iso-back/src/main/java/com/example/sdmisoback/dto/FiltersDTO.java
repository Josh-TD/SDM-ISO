package com.example.sdmisoback.dto;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.domain.PageRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    public LocalDateTime propPeriodBeginDate;
    public LocalDateTime propPeriodEndDate;

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
    public LocalDateTime aucBeginDate;
    public LocalDateTime aucEndDate;

    // commitment period
    public Integer commitPeriodId;
    public List<String> commitPeriodTypes;
    public String commitPeriodDesc;
    public LocalDateTime commitPeriodBeginDate;
    public LocalDateTime commitPeriodEndDate;

    // auction period
    public Integer aucPeriodId;
    public List<String> aucPeriodTypes;
    public String aucPeriodDesc;
    public LocalDateTime aucPeriodBeginDate;
    public LocalDateTime aucPeriodEndDate;
}