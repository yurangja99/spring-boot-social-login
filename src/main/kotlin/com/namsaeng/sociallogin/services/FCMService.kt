package com.namsaeng.sociallogin.services

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.WebpushConfig
import com.google.firebase.messaging.WebpushNotification
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutionException


@Service
class FCMService {
    private val log: Log = LogFactory.getLog("FCMServiceLogger")

    // 푸시 알림 보내기
    @Throws(InterruptedException::class, ExecutionException::class)
    fun send(notificationRequest: NotificationRequest) {
        val message: Message = Message.builder()
                .setToken(notificationRequest.token)
                .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
                        .setNotification(WebpushNotification(
                                notificationRequest.title,
                                notificationRequest.message
                        ))
                        .build())
                .build()
        val response = FirebaseMessaging.getInstance().sendAsync(message).get()
        log.info("Sent message: $response")
    }
}