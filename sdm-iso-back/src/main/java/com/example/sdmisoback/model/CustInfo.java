package com.example.sdmisoback.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CustInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private int customerId;

    @Column(name="customer_name", length = 30, nullable = false)
    private String customerName;
}
