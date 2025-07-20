package com.Ai.Courier.model;


import jakarta.persistence.*;

@Entity
@Table(name="orderObject")
public class OrderObject {

    @Id
    @Column(name="orderId")
    Integer orderId;

    @Column(name="sendCustomerId",nullable = false)
    Integer sendCustomerId;

    @Column(name="receiveCustomerId",nullable = false)
    Integer receiveCustomerId;

    @Column(name="senderAddressId",nullable = false)
    Integer senderAddressId;

    @Column(name="receiverAddressId",nullable = false)
    Integer receiverAddressId;

    @Column(name="status")
    String status;

    @ManyToOne
    @JoinColumn(name="sendCustomerId",insertable = false,updatable = false,foreignKey = @ForeignKey(name="fk_CO_OB_S"))
    CustomerObject customerObject_FromOrderObjectAsSender;

    @ManyToOne
    @JoinColumn(name="receiveCustomerId",insertable = false,updatable = false,foreignKey = @ForeignKey(name="fk_CO_OB_R"))
    CustomerObject customerObject_FromOrderObjectAsReceiver;

    @ManyToOne
    @JoinColumn(name="senderAddressId",insertable = false,updatable = false,foreignKey = @ForeignKey(name="fk_CO_CAO_S"))
    CustomerAddressObject customerAddressObject_FromOrderObjectAsSender;

    @ManyToOne
    @JoinColumn(name="receiverAddressId",insertable = false,updatable = false,foreignKey = @ForeignKey(name="fk_CO_CAO_R"))
    CustomerAddressObject customerAddressObject_FromOrderObjectAsReceiver;




}
