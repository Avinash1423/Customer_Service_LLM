package com.Ai.Courier.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name="changeLogger")
public  class changeLoggerObject  {

@Id
@Column(name="logId",nullable = false)
Integer logId;

@Column(name="userId",nullable = false)
Integer userId;

@Column(name="orderId",nullable = false)
Integer orderId;

@Column(name="description",nullable = false)
String description;

@Column(name="date",nullable = false)
LocalDate date;

@Column(name="time",nullable = false)
Time time;


}
