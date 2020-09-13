package com.namsaeng.sociallogin.controllers

import com.namsaeng.sociallogin.configs.auth.dtos.SessionUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpSession

@Controller
class HomeController {
    @Autowired
    private lateinit var httpSession: HttpSession

    @GetMapping("/")
    private fun home(model: Model): String {
        val user: SessionUser? = httpSession.getAttribute("user") as SessionUser?
        if (user != null) {
            model.addAttribute("userName", user.name)
            model.addAttribute("userEmail", user.email)
            model.addAttribute("userPicture", user.picture ?: "null")
            println("User Info at Controller: ")
            println("userName: ${user.name}")
            println("userEmail: ${user.email}")
            println("userPicture: ${user.picture ?: "null"}\n")
        } else {
            println("null user")
        }
        return "index"
    }

    @GetMapping("/guest")
    private fun index(): String {
        return "guest"
    }
}