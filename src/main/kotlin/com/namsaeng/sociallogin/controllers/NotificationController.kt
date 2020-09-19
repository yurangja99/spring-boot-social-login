package com.namsaeng.sociallogin.controllers

import com.namsaeng.sociallogin.configs.auth.dtos.SessionUser
import com.namsaeng.sociallogin.entities.User
import com.namsaeng.sociallogin.repositories.UserRepository
import com.namsaeng.sociallogin.services.FCMService
import com.namsaeng.sociallogin.services.NotificationRequest
import javassist.NotFoundException
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView
import javax.servlet.http.HttpSession

@RestController
class NotificationController {
    @Autowired
    private lateinit var httpSession: HttpSession
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var fcmService: FCMService

    private val log: Log = LogFactory.getLog("NotificationControllerLogger")

    // 파이어베이스 토큰 등록
    @PatchMapping("/register")
    private fun register (token: String) {
        log.info("Accessed /register")
        val user: SessionUser = httpSession.getAttribute("user")!! as SessionUser
        val userEntity: User = userRepository.findById(user.id).orElse(null)
                ?: throw NotFoundException("no such user")
        log.info("Firebase token for user ${userEntity.id}: $token")

        userRepository.save(
                userEntity.update(
                        userEntity.name, userEntity.picture,
                        userEntity.accessToken, token
                )
        )
    }

    // 자신에게 푸시 알림 보내기
    @PostMapping("/self/message")
    private fun sendMessageToSelf () {
        log.info("Accessed /self/message")
        val user: SessionUser = httpSession.getAttribute("user")!! as SessionUser
        val userEntity: User = userRepository.findById(user.id).orElse(null)
                ?: throw NotFoundException("no such user")
        if (userEntity.fbToken == null) {
            log.error("User ${userEntity.id} doesn't have Firebase token! Check service worker and patch /register.")
        } else {
            log.info("User ${userEntity.id} have Firebase token ${userEntity.fbToken}")
            val request: NotificationRequest = NotificationRequest(
                    "제목", "메시지 내용", userEntity.fbToken!!
            )
            fcmService.send(request)
        }
    }
}