package com.Ai.Courier.webSocket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="available")
public class AvailableAgents {

    @Id
    @Column(name="agentId")
    Integer AgentId;

    public Integer getAgentId() {
        return AgentId;
    }

    public void setAgentId(Integer agentId) {
        AgentId = agentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name="status",nullable = false)
    String status;


    public AvailableAgents() {
    }
}
