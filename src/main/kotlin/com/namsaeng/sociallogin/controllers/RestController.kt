package com.namsaeng.sociallogin.controllers

import com.namsaeng.sociallogin.entities.User
import com.namsaeng.sociallogin.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RestController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @GetMapping("users")
    private fun getUsers (pageable: Pageable): Page<User> {
        return userRepository.findAll(pageable)
    }
}