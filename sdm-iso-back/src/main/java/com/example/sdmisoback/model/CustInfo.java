package com.example.sdmisoback.model;

import jakarta.persistence.*;

@Entity
public class CustInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private int customerId;

    @Column(name="customer_name", length = 30, nullable = false)
    private String customerName;
    
    // constructers
    public CustInfo() {}

    public CustInfo(String customerName) {
        this.customerName = customerName;
    }

    // gets and sets
    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }
}
