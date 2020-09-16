package com.namsaeng.sociallogin.controllers

import com.namsaeng.sociallogin.entities.User
import com.namsaeng.sociallogin.repositories.UserRepository
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RestController {
    @Autowired
    private lateinit var userRepository: UserRepository

    private val log: Log = LogFactory.getLog("RestControllerLogger")

    // Health check용 API
    @GetMapping("health")
    private fun healthCheck (): String {
        log.info("Accessed /health to do Health Check")
        return "healthy"
    }

    // 유저 리스트 반환
    @GetMapping("users")
    private fun getUsers (pageable: Pageable): Page<User> {
        log.info("Accessed /users")
        return userRepository.findAll(pageable)
    }
}