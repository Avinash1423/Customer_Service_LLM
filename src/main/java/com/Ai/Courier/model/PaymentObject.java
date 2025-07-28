package com.Ai.Courier.model;

import jakarta.persistence.*;

@Entity
@Table(name="payment")
public class PaymentObject {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="orderId")
    Integer orderId;


    @Column(name="paymentStatus")
    String paymentStatus;///  paid or underpayRecovery


    @Column(name="chargedAmount")
    Long chargedAmount;

    public PaymentObject(Integer orderId,String paymentStatus) {
        this.orderId=orderId;
        this.paymentStatus=paymentStatus;
    }


    public PaymentObject(String paymentStatus, Long chargedAmount){

        this.paymentStatus=paymentStatus;
        this.chargedAmount=chargedAmount;


    }

    public PaymentObject() {

    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Long getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(Long chargedAmount) {
        this.chargedAmount = chargedAmount;
    }
}
