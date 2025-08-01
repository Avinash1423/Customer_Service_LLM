package com.Ai.Courier;

import com.Ai.Courier.model.CustomerObject;
import com.Ai.Courier.model.CustomerObjectRepository;
import com.Ai.Courier.model.MessageHistoryObject;
import com.Ai.Courier.model.MessageHistoryObjectRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class LoginController {


    CustomerObjectRepository customerObjectRepository;
    MessageHistoryObjectRepository messageHistoryObjectRepository;

    public LoginController(CustomerObjectRepository customerObjectRepository,MessageHistoryObjectRepository messageHistoryObjectRepository) {
        this.customerObjectRepository = customerObjectRepository;
        this.messageHistoryObjectRepository=messageHistoryObjectRepository;
    }


    @GetMapping("/loginPage")
    public String LoginPage() {

        return "LoginScreen";

    }

    @PostMapping("/validateLogin")
    public String LoginMethod(@RequestParam(required = false) String email, @RequestParam(required = false) String password, Model model, HttpSession session) {


        if (email == null || email.isBlank() || password == null || password.isBlank()) {

            model.addAttribute("emptyFieldError", "Please fill all fields");
            return "LoginScreen";
        }

        // validate email and password

        session.setAttribute("loggedInEmail",email);
        CustomerObject loggedInCustomer= customerObjectRepository.findByCustomerEmail(email);
        Integer loggedInUser=loggedInCustomer.getCustomerId();
        session.setAttribute("loggedInUser",loggedInUser);

        List<MessageHistoryObject> listOfMessages=messageHistoryObjectRepository.findByUserId(loggedInUser);

        model.addAttribute("listOfMessages",listOfMessages);

        return "ChatScreen";

    }

    @GetMapping("/errorHome")
    public String errorResponse(){


        return "LoginScreen";
    }
}
