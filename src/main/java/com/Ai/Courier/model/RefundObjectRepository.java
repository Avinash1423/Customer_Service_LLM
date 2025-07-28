package com.Ai.Courier.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefundObjectRepository extends JpaRepository<RefundObject,Integer> {

     RefundObject findByOrderId(Integer orderId);
}
