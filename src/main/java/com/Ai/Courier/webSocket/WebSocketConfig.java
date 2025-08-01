package com.Ai.Courier.webSocket;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/// webServer
@Component
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    public WebSocketConfig(){
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry){


      messageBrokerRegistry.enableSimpleBroker("/queue");//out from server

      messageBrokerRegistry.setApplicationDestinationPrefixes("/app");// in to the server

        messageBrokerRegistry.setUserDestinationPrefix("/user");


    }

          @Override
           public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry){

            stompEndpointRegistry.addEndpoint("/ws").withSockJS();

          }

}
