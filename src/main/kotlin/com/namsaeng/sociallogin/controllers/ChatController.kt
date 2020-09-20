package com.namsaeng.sociallogin.controllers

import com.namsaeng.sociallogin.entities.ChatMessage
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.stereotype.Controller

@Controller
class ChatController {
    private val log: Log = LogFactory.getLog("ChatControllerLogger")

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    fun sendMessage(@Payload chatMessage: ChatMessage): ChatMessage {
        log.info("Message sent by ${chatMessage.sender}: ${chatMessage.content}")
        return chatMessage
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    fun addUser(
            @Payload chatMessage: ChatMessage,
            headerAccessor: SimpMessageHeaderAccessor
    ): ChatMessage {
        headerAccessor.sessionAttributes?.set("username", chatMessage.sender)
        return chatMessage
    }
}