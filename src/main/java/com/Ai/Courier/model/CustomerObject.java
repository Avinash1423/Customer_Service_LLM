package com.Ai.Courier.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customercustomer")
public class CustomerObject {

    @Id
    @Column(name="customerId",unique = true,nullable = false)
    Integer customerId;

    @Column(name="customerFirstName",nullable = false)
    String customerFirstName;

    @Column(name="customerLastName",nullable = false)
    String customerLastName;

    @Column(name="customerPhoneNumber",unique = true,nullable = false)
    String  customerPhoneNumber;

    @Column(name="customerEmail",unique = true,nullable = false)
    String customerEmail;

    @OneToMany(mappedBy ="customerObjectFromAddressObject")
    List<CustomerAddressObject> listOfAddressess= new ArrayList<>();

    @OneToMany(mappedBy="customerObject_FromOrderObjectAsSender")
    List<OrderObject> listofOrdersAsSender = new ArrayList<>();

    @OneToMany(mappedBy="customerObject_FromOrderObjectAsReceiver" )
    List<OrderObject> listofOrdersAsReceiver=new ArrayList<>();

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<CustomerAddressObject> getListOfAddressess() {
        return listOfAddressess;
    }

    public void setListOfAddressess(List<CustomerAddressObject> listOfAddressess) {
        this.listOfAddressess = listOfAddressess;
    }
}
