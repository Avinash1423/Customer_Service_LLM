package com.Ai.Courier;

import com.Ai.Courier.model.MessageHistoryObjectRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.mockito.Mockito;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;


import java.lang.reflect.Field;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ChatController.class)
class ChatControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    MessageHistoryObjectRepository messageHistoryObjectRepository;

    @MockitoBean
    ParseAndRender parseAndRender;

    @MockitoBean
    ServiceTools ServiceTools;

    @MockitoBean
    ChatClient.ChatClientRequestSpec chatClientRequestSpec;

    @MockitoBean
    ChatClient.CallResponseSpec callResponseSpec;

    @MockitoBean
    ChatClient chatClient;



    @Test
    void chatControllerMethodTest() throws Exception {


        MockHttpSession mockHttpSession=new MockHttpSession();

       mockHttpSession.setAttribute("loggedInUser",1);

        mockHttpSession.setAttribute("loggedInEmail","fake@gmail.com");

        String systemPrompt= "uselssSystemPrompt";

        when(chatClient.prompt()).thenReturn(chatClientRequestSpec);
        when(chatClientRequestSpec.system(any(Resource.class))).thenReturn(chatClientRequestSpec);
        when(chatClientRequestSpec.user("SomeUselessInput")).thenReturn(chatClientRequestSpec);
        when(chatClientRequestSpec.tools(any())).thenReturn(chatClientRequestSpec);
        when(chatClientRequestSpec.call()).thenReturn(callResponseSpec);
        when(callResponseSpec.content()).thenReturn("RawResponse");


       when(parseAndRender.ParseAndRenderMethod("RawResponse")).thenReturn("Response");

       when(messageHistoryObjectRepository.save(any())).thenReturn(null);

       when(messageHistoryObjectRepository.findByUserId(1)).thenReturn(Collections.emptyList());


       mockMvc.perform(post("/chat").param("userInput","SomeUselessInput").session(mockHttpSession)).andExpect(status().isOk()).andExpect(view().name("ChatScreen")).andExpect(model().attributeExists("listOfMessages"));
//    mockMvc.perform(post("/chat").param("userInput","SomeUselessInput").session(mockHttpSession)).andExpect(status().isOk()).andExpect(view().name("ChatScreen"));

    }

}