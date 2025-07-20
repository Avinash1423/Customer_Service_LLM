package com.Ai.Courier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="additonalCharge")
public class AdditonalChargeObject {

    @Id
    @Column(name="addtionalChargeId")
    Integer addtionalChargeId;

    @Column(name="orderId")
    Integer orderId;

    @Column(name="additionalFees")
    Integer additionalFees;

    @Column(name="reason")
    String reason;

}
