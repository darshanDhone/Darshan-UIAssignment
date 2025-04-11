package com.infosys.customer_reward_assignment.dto;

import java.util.Date;

public class TransactionDTO {

    private Long customerId;
    private String spendDetails;
    private Double amount;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSpendDetails() {
        return spendDetails;
    }

    public void setSpendDetails(String spendDetails) {
        this.spendDetails = spendDetails;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}

