package com.infosys.customer_reward_assignment.model;

import jakarta.persistence.*;

//Could have used lombok but was not supporting in my system
@Entity
@Table(name = "reward_points")
public class RewardPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "month_")
    private Integer month;

    @Column(name = "year_")
    private Integer year;

    private Integer points;

    public RewardPoint() {
    }

    public RewardPoint(Customer customer, Integer points, Integer year, Integer month) {
        this.customer = customer;
        this.points = points;
        this.year = year;
        this.month = month;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
