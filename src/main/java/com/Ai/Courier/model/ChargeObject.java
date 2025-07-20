package com.Ai.Courier.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="charge")
public class ChargeObject {

    @Id
    Integer orderId;

    @Column(name="orderWeight")
    Long orderWeight;

    @Column(name="orderDistance")
    Long orderDistance;

    @Column(name="additionalFees")
    Long additionalFees;

    //// orderCharge-->( per kg  price * weight) + (per km price * distance)

    //not a separate column to maintain db normalization,can be obtained by custom query.








}
