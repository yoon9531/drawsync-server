package com.sy.drawsyncserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 웹 브라우저용 (SockJS 지원)
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
        // 네이티브 앱용 (순수 WebSocket)
        registry.addEndpoint("/ws-native").setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic/"); // 클라이언트로 메시지를 보낼 때
        registry.setApplicationDestinationPrefixes("/app"); // 클라이언트에서 메시지를 보낼 때
    }
}
