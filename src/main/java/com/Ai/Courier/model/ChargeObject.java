package com.Ai.Courier.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="charge")
public class ChargeObject {

    public ChargeObject(){

    }

    @Id
    Integer orderId;

    @Column(name="orderWeight")
    Long orderWeight;

    @Column(name="orderDistance")
    Long orderDistance;

    @Column(name="additionalFees")
    Integer additionalFees;

    public ChargeObject(Integer orderId, Long orderWeight, Long orderDistance, Integer additionalFees) {
        this.orderId = orderId;
        this.orderWeight = orderWeight;
        this.orderDistance = orderDistance;
        this.additionalFees = additionalFees;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(Long orderWeight) {
        this.orderWeight = orderWeight;
    }

    public Long getOrderDistance() {
        return orderDistance;
    }

    public void setOrderDistance(Long orderDistance) {
        this.orderDistance = orderDistance;
    }

    public Integer getAdditionalFees() {
        return additionalFees;
    }

    public void setAdditionalFees(Integer additionalFees) {
        this.additionalFees = additionalFees;
    }

    //// orderCharge-->( per kg  price * weight) + (per km price * distance)

    //not a separate column to maintain db normalization,can be obtained by custom query.








}
