package com.Ai.Courier.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerAddressObjectRepository extends JpaRepository<CustomerAddressObject,Integer> {


    @Query("Select new com.Ai.Courier.model.CustomerAddressObject(c.addressId,c.line1,c.line2,c.province,c.district,c.city,c.postalCode) FROM CustomerAddressObject c WHERE c.customerId= :customerId")
    List<CustomerAddressObject> findReceiverAddress(@Param("customerId") Integer customerId);


    @Query("Select new com.Ai.Courier.model.CustomerAddressObject(c.addressId,c.line1,c.line2,c.province,c.district,c.city,c.postalCode) FROM CustomerAddressObject c WHERE c.customerId= :customerId AND c.addressId != :addressId")
    List<CustomerAddressObject>showReceiversAddresses(@Param("customerId")Integer customerId ,@Param("addressId")Integer addressId);
}
