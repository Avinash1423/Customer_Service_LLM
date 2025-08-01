package com.Ai.Courier.webSocket;


public class MessageObject {

    String sender;

    String receiver;

    String Message;


    public MessageObject() {

    }

    public MessageObject(String sender, String receiver, String Message) {

        this.receiver=receiver;
        this.Message=Message;
        this.sender=sender;

    }



    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }



    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}


