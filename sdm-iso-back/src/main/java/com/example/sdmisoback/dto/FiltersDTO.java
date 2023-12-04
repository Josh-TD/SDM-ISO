package com.example.sdmisoback.dto;

import java.time.LocalDateTime;
import java.time.LocalDate;
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
}
