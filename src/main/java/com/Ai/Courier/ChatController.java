package com.Ai.Courier;

import com.Ai.Courier.model.MessageHistoryObject;
import com.Ai.Courier.model.MessageHistoryObjectRepository;
import com.Ai.Courier.webSocket.AvailableAgentRepository;
import com.Ai.Courier.webSocket.AvailableAgents;
import jakarta.servlet.http.HttpSession;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChatController {

    @Autowired
    ChatClient chatClient;
    @Autowired
    MessageHistoryObjectRepository messageHistoryObjectRepository;
    @Autowired
    ParseAndRender parseAndRender;
    @Autowired
    ServiceTools ServiceTools;
    @Autowired
    AvailableAgentRepository availableAgentRepository;

    @Value("classpath:/docs/SystemPrompt.txt")
    Resource SystemPrompt;

    @PostMapping("/chat")
    public String chatControllerMethod(@RequestParam(required = false) String userInput , HttpSession httpsession, Model model){


        String loggedInEmail=httpsession.getAttribute("loggedInEmail").toString();

        Integer loggedInUserId=(Integer) httpsession.getAttribute("loggedInUser");

        String response=chatClient.prompt().system(SystemPrompt).user(userInput).tools(ServiceTools).call().content();

        String finalResponse=parseAndRender.ParseAndRenderMethod(response);

        MessageHistoryObject newMessage=new MessageHistoryObject(loggedInUserId,userInput,finalResponse);

        messageHistoryObjectRepository.save(newMessage);

        List<MessageHistoryObject> listOfMessages=messageHistoryObjectRepository.findByUserId(loggedInUserId);

        model.addAttribute("listOfMessages",listOfMessages);

        System.out.println(finalResponse);
        if (finalResponse.trim().contains("<p>Got it. Connecting you to an agent.</p>")){

            System.out.println("LLM matched. Redirecting now...");

            HttpSession session=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();

            AvailableAgents availableAgent= availableAgentRepository.findAnAgent();

            System.out.println("goToChatPageCustomer"+availableAgent.getAgentId());

            Integer sender= (Integer) session.getAttribute("loggedInUser");

            model.addAttribute("sender",sender);
            model.addAttribute("receiver",availableAgent.getAgentId());

            System.out.println("goToChatPageCustomer, sender: " +sender);
            System.out.println("goToChatPageCustomer, receiver: " +availableAgent.getAgentId());

            /// ****
            session.setAttribute("sender",availableAgent.getAgentId());
            session.setAttribute("receiver",sender);
            /// ***

            System.out.println("goToChatPageCustomer, models sent: " );
            return "redirect:/chatterCustomer";


        }

           return "ChatScreen";

    }


}
