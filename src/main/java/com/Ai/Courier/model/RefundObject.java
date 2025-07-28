package com.Ai.Courier.model;


import jakarta.persistence.*;

@Entity
@Table(name="refunds")
public class RefundObject {


    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="refundId")
    Integer refundId;

    @Column(name="orderId")
    Integer orderId;

    @Column(name="refundAmount")
    Long refundAmount;

    @Column(name="refundReason")
    String refundReason;


    public RefundObject(Integer orderId, Long refundAmount, String refundReason) {
        this.orderId = orderId;
        this.refundAmount = refundAmount;
        this.refundReason = refundReason;
    }

    public RefundObject() {
    }
}
