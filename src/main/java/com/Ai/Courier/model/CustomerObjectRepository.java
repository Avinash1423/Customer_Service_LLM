package com.Ai.Courier.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerObjectRepository extends JpaRepository<CustomerObject,Integer> {

    CustomerObject  findByCustomerEmail(String customerEmail);

}
