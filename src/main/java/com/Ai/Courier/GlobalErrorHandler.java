package com.Ai.Courier;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalErrorHandler {


@Autowired
@Qualifier("basic")
ChatClient chatClient;


    @ExceptionHandler(NullPointerException.class)
    public String nullPointerException(NullPointerException ex, Model model){


        ex.printStackTrace();
        String errorMessagePrompt="Explain what this error message means to a user simply: " + ex.getMessage();


        String errorMessage= chatClient.prompt(errorMessagePrompt).call().content();

        model.addAttribute("errorMessage",errorMessage);


        return  "errorPage";


    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public String HttpClientErrorException(HttpClientErrorException ex, Model model){

        ex.printStackTrace();
        String errorMessage=ex.getMessage();



        model.addAttribute("errorMessage",errorMessage);


        return  "errorPage";

    }






}
