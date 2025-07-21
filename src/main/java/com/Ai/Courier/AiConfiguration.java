package com.Ai.Courier;

import jakarta.servlet.http.HttpSession;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.neo4j.Neo4jChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class AiConfiguration {

 Neo4jChatMemoryRepository neo4jChatMemoryRepository;

    public AiConfiguration(Neo4jChatMemoryRepository neo4jChatMemoryRepository) {
        this.neo4jChatMemoryRepository = neo4jChatMemoryRepository;
    }



    @Bean
    public ChatMemory chatMemory(){

        return MessageWindowChatMemory.builder().chatMemoryRepository(neo4jChatMemoryRepository).maxMessages(10).build();

    }

    @Bean
    @RequestScope
    public ChatClient chatClient(ChatClient.Builder builder, ChatMemory chatMemory,HttpSession httpSession){

        // anoyomous user

       Integer userId= (Integer) httpSession.getAttribute("loggedInUser");
        return builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).conversationId(String.valueOf(userId)).build()).build();

    }
}
