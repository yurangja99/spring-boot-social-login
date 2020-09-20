package com.namsaeng.sociallogin.controllers

import com.namsaeng.sociallogin.entities.ChatMessage
import com.namsaeng.sociallogin.entities.MessageType
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectedEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent

@Component
class WebSocketEventListener {
    @Autowired
    private lateinit var messagingTemplate: SimpMessageSendingOperations

    private val log: Log = LogFactory.getLog("WebSocketEventListenerLogger")

    @EventListener
    fun handleWebSocketConnectListener(event: SessionConnectedEvent) {
        log.info("New User Connected.")
    }

    @EventListener
    fun handleWebSocketDisconnectListener(event: SessionDisconnectEvent) {
        val headerAccessor: StompHeaderAccessor = StompHeaderAccessor.wrap(event.message)
        val username: String? = headerAccessor.sessionAttributes?.get("username") as String?
        if (username != null) {
            log.info("User Disconnected: $username")
            val chatMessage: ChatMessage = ChatMessage(
                    type = MessageType.LEAVE,
                    content = "leave",
                    sender = username
            )
            messagingTemplate.convertAndSend("/topic/public", chatMessage)
        }
    }
}