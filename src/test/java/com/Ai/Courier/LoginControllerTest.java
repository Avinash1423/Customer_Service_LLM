package com.Ai.Courier;

import com.Ai.Courier.model.CustomerObject;
import com.Ai.Courier.model.CustomerObjectRepository;
import com.Ai.Courier.model.MessageHistoryObjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.ai.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CustomerObjectRepository customerObjectRepository;

    @MockitoBean
    MessageHistoryObjectRepository messageHistoryObjectRepository;



    @Test
     void testLoginPageLoad() throws Exception {


        mockMvc.perform(get("/loginPage")).andExpect(status().isOk()).andExpect(view().name("LoginScreen"));

    }

    @Test
    void testEmptyParameters() throws Exception {

        mockMvc.perform(post("/validateLogin").param("email","").param("password","")).andExpect(status().isOk()).andExpect(view().name("LoginScreen")).andExpect(model().attributeExists("emptyFieldError"));

    }

   @Test
    void testSuccessfulLogin() throws Exception {

       CustomerObject fakeCustomer=new CustomerObject();

       fakeCustomer.setCustomerEmail("fake@gmail.com");
       fakeCustomer.setCustomerId(1);


       when(customerObjectRepository.findByCustomerEmail("fake@gmail.com")).thenReturn(fakeCustomer);
       when(messageHistoryObjectRepository.findByUserId(1)).thenReturn(Collections.emptyList());


       mockMvc.perform(post("/validateLogin").param("email","fake@gmail.com").param("password","1234")).andExpect(status().isOk()).andExpect(view().name("ChatScreen")).andExpect(model().attributeExists("listOfMessages")).andExpect(request().sessionAttribute("loggedInUser",1));



   }

}