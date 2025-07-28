package com.Ai.Courier.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="CustomerAddress")
public class CustomerAddressObject {

    @Id
    @Column(name="addressId",nullable = false)
    Integer addressId;

   @Column(name="customerId")
    Integer customerId;

    @Column(name="line1",nullable = false)
    String  line1;

    @Column(name="line2")
    String line2;

    @Column(name="province",nullable = false)
    String province;

    @Column(name="district",nullable = false)
    String district;

    @Column(name="city",nullable = false)
    String city;

    @Column(name="postalCode",nullable = false)
    Integer postalCode;

    @ManyToOne
    @JoinColumn(name="customerId",insertable = false,updatable = false, foreignKey = @ForeignKey(name="fk_CAO_CA"))
    CustomerObject customerObjectFromAddressObject;

    @OneToMany(mappedBy="customerAddressObject_FromOrderObjectAsSender")
    List<OrderObject> listOfAddressesAsSender;


    @OneToMany(mappedBy="customerAddressObject_FromOrderObjectAsReceiver")
    List<OrderObject> listOfAddressesAsReceiver;

    public CustomerAddressObject(Integer addressId,  String line1, String line2, String province, String district, String city, Integer postalCode) {
        this.addressId = addressId;
        this.line1 = line1;
        this.line2 = line2;
        this.province = province;
        this.district = district;
        this.city = city;
        this.postalCode = postalCode;
    }

    public CustomerAddressObject() {
    }


    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public CustomerObject getCustomerObjectFromAddressObject() {
        return customerObjectFromAddressObject;
    }

    public void setCustomerObjectFromAddressObject(CustomerObject customerObjectFromAddressObject) {
        this.customerObjectFromAddressObject = customerObjectFromAddressObject;
    }



}
