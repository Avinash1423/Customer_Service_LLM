package com.Ai.Courier.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="MessageHistory")
public class MessageHistoryObject {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MessageId")
    Integer MessageId;

    @Column(name="userId",nullable = false)
    Integer userId;

    @Column(name="question",nullable = false,length = 1000)
    String question;

    @Column(name="answer",nullable = false,length = 1000)
    String answer;

    @Column(name="date")
    LocalDate date;


    public MessageHistoryObject(){}

    public MessageHistoryObject(Integer userId,String question,String answer){
        this.userId=userId;
        this.question=question;
        this.answer=answer;
        this.date=LocalDate.now();

    }

    public Integer getMessageId() {
        return MessageId;
    }

    public void setMessageId(Integer messageId) {
        MessageId = messageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
