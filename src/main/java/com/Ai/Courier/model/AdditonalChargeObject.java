package com.Ai.Courier.model;

import jakarta.persistence.*;

@Entity
@Table(name="additionalCharge")
public class AdditonalChargeObject {
    public Integer getAddtionalChargeId() {
        return addtionalChargeId;
    }

    public void setAddtionalChargeId(Integer addtionalChargeId) {
        this.addtionalChargeId = addtionalChargeId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getAdditionalFees() {
        return additionalFees;
    }

    public void setAdditionalFees(Integer additionalFees) {
        this.additionalFees = additionalFees;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name="addtionalChargeId")
    Integer addtionalChargeId;

    @Column(name="orderId")
    Integer orderId;

    @Column(name="additionalFees")
    Integer additionalFees;

    @Column(name="reason")
    String reason;

    public AdditonalChargeObject(Integer orderId, Integer additionalFees, String reason) {
        this.orderId = orderId;
        this.additionalFees = additionalFees;
        this.reason = reason;
    }

    public AdditonalChargeObject() {
    }
}
