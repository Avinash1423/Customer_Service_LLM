package com.Ai.Courier.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageHistoryObjectRepository extends JpaRepository<MessageHistoryObject,Integer> {


    List<MessageHistoryObject> findByUserId(Integer userId);
}