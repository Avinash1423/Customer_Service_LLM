package com.Ai.Courier.webSocket;

import com.Ai.Courier.model.MessageHistoryObjectRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Controller
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;



    @MessageMapping("/messageAgent")
    public void handleMessageAgent(MessageObject messageObject){

     //  HttpSession session=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();


        System.out.println("FROM handleMessageAgent "+ messageObject.getReceiver());
        System.out.println("FROM handleMessageAgent "+ messageObject.getSender());
        System.out.println("FROM handleMessageAgent "+ messageObject.getMessage());

        simpMessagingTemplate.convertAndSend("/queue/messages-" + messageObject.getReceiver(),messageObject);

        simpMessagingTemplate.convertAndSend("/queue/messages-" + messageObject.getSender(),messageObject);
    }

    @MessageMapping("/messageCustomer")
    public void handleMessageCustomer(MessageObject messageObject){

     //   HttpSession session=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();


        System.out.println("FROM handleMessageCustomer "+ messageObject.getReceiver());
        System.out.println("FROM handleMessageCustomer "+ messageObject.getSender());
        System.out.println("FROM handleMessageCustomer  "+ messageObject.getMessage());

        simpMessagingTemplate.convertAndSend("/queue/messages-" + messageObject.getReceiver(),messageObject);
        simpMessagingTemplate.convertAndSend("/queue/messages-" + messageObject.getSender(),messageObject);

    }

    @MessageMapping("/join")
    public void  messageHistoryUpdate(String receiver){



           // simpMessagingTemplate.convertAndSend("/queue/messages-"+message.getReceiver(),messageObject);

        }

    }



