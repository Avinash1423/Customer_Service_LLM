package com.Ai.Courier.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="issueReference")
public class issueReferenceObject {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="issueId")
    Integer issueId;

    @Column(name="userId",nullable = false)
    Integer userId;

    @Column(name="orderId",nullable = false)
    Integer orderId;

    @Column(name="date",nullable = false)
    LocalDate date;

    @Column(name="time",nullable = false)
    LocalTime time;

    @Column(name="description",nullable = false)
    String description;

    public issueReferenceObject() {
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public issueReferenceObject(Integer userId, Integer orderId, String description) {

        this.userId = userId;
        this.orderId = orderId;
        this.description=description;
        this.date=LocalDate.now();
        this.time= LocalTime.now();

    }
}

