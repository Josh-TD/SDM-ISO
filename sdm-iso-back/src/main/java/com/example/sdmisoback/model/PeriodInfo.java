package com.example.sdmisoback.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Entity
public class PeriodInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="period_id")
    private int periodId;

    @Column(name="period_type", length = 20, nullable = false)
    private String periodType;

    @Column(name="description", length = 50, nullable = false)
    private String description;

    @Column(name="begin_date", nullable = false)
    private LocalDateTime beginDate;

    @Column(name="end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name="parent_period_id")
    private Integer parentPeriodId;

    // constructors
    public PeriodInfo() {}

    public PeriodInfo(String periodType, String description, LocalDateTime beginDate, LocalDateTime endDate, Integer parentPeriodId) {
        this.periodType = periodType;
        this.description = description;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.parentPeriodId = parentPeriodId;
    }


    // gets and set
    public int getPeriodId() {
        return periodId;
    }

    public String getPeriodType() {
        return periodType;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Integer getParentPeriodId() {
        return parentPeriodId;
    }
}
