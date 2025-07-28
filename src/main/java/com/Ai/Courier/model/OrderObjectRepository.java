package com.Ai.Courier.model;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderObjectRepository extends JpaRepository<OrderObject,Integer> {

     @Modifying
    @Transactional
    @Query("UPDATE OrderObject o SET o.receiverAddressId=  :newReceiverAddressId WHERE o.orderId = :orderId")
    void updateAddress(@Param("newReceiverAddressId")Integer newReceiverAddressId, @Param("orderId") Integer orderId);

    @Modifying
    @Transactional
    @Query("UPDATE OrderObject o SET o.receiveCustomerId= :newReceiverId ,o.receiverAddressId= :newAddressId WHERE o.orderId = :orderId")
    void updateReceiver(@Param("newReceiverId") Integer newReceiverId ,@Param("newAddressId") Integer newAddressId, @Param("orderId") Integer orderId );


    @Modifying
    @Transactional
    @Query("UPDATE OrderObject o SET o.status='cancelled' WHERE o.orderId= :orderId")
    void setStatusAsCancel(@Param("orderId")Integer orderId);

    List<OrderObject> findOrderObjectBySendCustomerId(Integer sendCustomerId);

    List<OrderObject> findOrderObjectByReceiveCustomerId(Integer ReceiveCustomerId);
}
