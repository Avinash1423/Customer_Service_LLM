package com.Ai.Courier.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="refunds")
public class RefundObject {

    @Id
    @Column(name="refundId")
    Integer refundId;

    @Column(name="orderId")
    Integer orderId;

    @Column(name="refundAmount")
    Integer refundAmount;

    @Column(name="refundReason")
    Integer refundReason;



}
