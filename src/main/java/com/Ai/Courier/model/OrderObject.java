package com.Ai.Courier.model;


import jakarta.persistence.*;

import java.time.LocalDate;

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

    @Column(name="dueDate")
    LocalDate dueDate;

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

    public OrderObject() {
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSendCustomerId() {
        return sendCustomerId;
    }

    public void setSendCustomerId(Integer sendCustomerId) {
        this.sendCustomerId = sendCustomerId;
    }

    public Integer getReceiveCustomerId() {
        return receiveCustomerId;
    }

    public void setReceiveCustomerId(Integer receiveCustomerId) {
        this.receiveCustomerId = receiveCustomerId;
    }

    public Integer getSenderAddressId() {
        return senderAddressId;
    }

    public void setSenderAddressId(Integer senderAddressId) {
        this.senderAddressId = senderAddressId;
    }

    public Integer getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(Integer receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
