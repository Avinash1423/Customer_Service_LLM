package com.Ai.Courier.webSocket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AvailableAgentRepository extends JpaRepository<AvailableAgents,Integer> {

    @Query(value = "SELECT * FROM available  WHERE status='Available' LIMIT 1",nativeQuery = true)
    AvailableAgents findAnAgent();


      }
