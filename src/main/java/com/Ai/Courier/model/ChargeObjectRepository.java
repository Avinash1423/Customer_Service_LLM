package com.Ai.Courier.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChargeObjectRepository extends JpaRepository<ChargeObject,Integer> {


    @Query("SELECT ((c.orderDistance*30)+(c.orderWeight*20)+(c.additionalFees)) AS orderCharge FROM ChargeObject c WHERE c.orderId = :orderId")
    public Integer getChargeDue(@Param("orderId")Integer orderId);

    ChargeObject findByOrderId(Integer orderId);

}
