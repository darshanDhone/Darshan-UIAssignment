package com.infosys.customer_reward_assignment.model;

import jakarta.persistence.*;

import java.util.Date;
//Could have used lombok but was not supporting in my system

@Entity
@Table(name = "customer_transactions")
public class CustomerTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String spendDetails;

    private Double amount;

    private Date date;

    public CustomerTransaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CustomerTransaction(Customer customer, String spendDetails, Double amount, Date date) {
        this.customer = customer;
        this.spendDetails = spendDetails;
        this.amount = amount;
        this.date = date;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomerID(Customer customer) {
        this.customer = customer;
    }

    public String getSpendDetails() {
        return spendDetails;
    }

    public void setSpendDetails(String spendDetails) {
        this.spendDetails = spendDetails;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
