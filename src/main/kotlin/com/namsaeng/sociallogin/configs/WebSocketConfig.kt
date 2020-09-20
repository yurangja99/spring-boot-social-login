package com.namsaeng.sociallogin.configs

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

// Configuration: 이 클래스가 Bean의 설정을 나타낸다.
// EnableWebSocketMessageBroker: 웹소켓 서버를 활성화한다.
@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig: WebSocketMessageBrokerConfigurer {
    // 클라이언트가 웹소켓 서버에 연결 시 사용할 웹소켓 엔드포인트 등록
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // SockJS를 사용하는데, 웹소켓을 지원하지 않는 브라우저를 고려하여 넣은 것임.
        registry.addEndpoint("/ws").withSockJS()
    }

    // 한 클라이언트에서 다른 클라이언트로 메시지를 보낼 때 사용할 브로커 구성
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        // /app으로 시작되는 메시지가 message-handling method로 라우팅 되어야 한다고 명시
        registry.setApplicationDestinationPrefixes("/app")
        // /topic으로 시작되는 메시지가 메시지 브로커로 라우팅 되어야 한다고 명시.
        // 메시지 브로커는 특정 주제를 구독한 모든 클라이언트로 메시지를 broadcast한다.
        registry.enableSimpleBroker("/topic")
    }
}