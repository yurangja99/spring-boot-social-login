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
    lateinit var httpSession: HttpSession

    @GetMapping("/")
    fun home(model: Model): String {
        val user: SessionUser? = httpSession.getAttribute("user") as SessionUser?
        if (user != null)
            model.addAttribute("userName", user.name)
        return "index"
    }

    @GetMapping("/guest")
    fun index(): String {
        return "guest"
    }
}