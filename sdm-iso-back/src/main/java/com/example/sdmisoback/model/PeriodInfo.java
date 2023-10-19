package com.example.sdmisoback.model;
import java.util.Date;

import jakarta.persistence.*;

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
    private Date beginDate;

    @Column(name="end_date", nullable = false)
    private Date endDate;

    @Column(name="parent_period_id")
    private int parentPeriodId;
}
