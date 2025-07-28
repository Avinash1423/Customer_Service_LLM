package com.Ai.Courier.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalChargeObjectRepository extends JpaRepository<AdditonalChargeObject,Integer> {


    AdditonalChargeObject findByOrderId(Integer orderId);
}
