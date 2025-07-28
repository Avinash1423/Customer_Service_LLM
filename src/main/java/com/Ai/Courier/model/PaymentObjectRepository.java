package com.Ai.Courier.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentObjectRepository extends JpaRepository<PaymentObject,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE PaymentObject p SET p.chargedAmount= :newAmount WHERE p.orderId= :orderId ")
    void updateChargedAmount(@Param("newAmount") Long newAmount,@Param("orderId") Integer orderId);

}
