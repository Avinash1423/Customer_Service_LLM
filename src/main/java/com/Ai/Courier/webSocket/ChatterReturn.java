package com.Ai.Courier.webSocket;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatterReturn {

    @Autowired
    AvailableAgentRepository availableAgentRepository;


    @GetMapping("/chatterAgent")
    public String goToChatPageAgent(Model model, HttpSession session){

        Integer sender=(Integer)  session.getAttribute("sender");
        Integer receiver=(Integer)  session.getAttribute("receiver");

        model.addAttribute("sender",sender);
        model.addAttribute("receiver",receiver);

        System.out.println("goToChatPageAgent sender: "+sender);
        System.out.println("goToChatPageAgent receiver: "+receiver);
        System.out.println("goToChatPageAgent, models sent: " );

        return "chatter";

    }

    @GetMapping("/chatterCustomer")
    public String goToChatPageCustomer(Model model, HttpSession session){

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
        return "chatterCustomer";


    }

}
