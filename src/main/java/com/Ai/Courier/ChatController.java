package com.Ai.Courier;

import com.Ai.Courier.model.MessageHistoryObject;
import com.Ai.Courier.model.MessageHistoryObjectRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChatController {

    ChatClient chatClient;
    MessageHistoryObjectRepository messageHistoryObjectRepository;

    public ChatController(ChatClient chatClient, MessageHistoryObjectRepository messageHistoryObjectRepository) {
        this.chatClient = chatClient;
        this.messageHistoryObjectRepository = messageHistoryObjectRepository;
    }


    @PostMapping("/chat")
    public String chatControllerMethod(@RequestParam(required = false) String userInput , HttpSession httpsession, Model model){


        String loggedInEmail=httpsession.getAttribute("loggedInEmail").toString();

        Integer loggedInUserId=(Integer) httpsession.getAttribute("loggedInUser");

        String response=chatClient.prompt(userInput).call().content();

        MessageHistoryObject newMessage=new MessageHistoryObject(loggedInUserId,userInput,response);

        messageHistoryObjectRepository.save(newMessage);

        List<MessageHistoryObject> listOfMessages=messageHistoryObjectRepository.findByUserId(loggedInUserId);

        model.addAttribute("listOfMessages",listOfMessages);


           return "ChatScreen";

    }


}
