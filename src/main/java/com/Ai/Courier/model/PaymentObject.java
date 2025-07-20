package com.Ai.Courier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="payment")
public class PaymentObject {


    @Id
    @Column(name="orderId")
    Integer orderId;


    @Column(name="paymentStatus")
    String paymentStatus;


    @Column(name="chargedAmount")
    Long chargedAmount;



}
