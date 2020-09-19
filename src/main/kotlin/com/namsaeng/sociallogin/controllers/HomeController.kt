package com.namsaeng.sociallogin.controllers

import com.namsaeng.sociallogin.configs.auth.dtos.SessionUser
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpSession

@Controller
class HomeController {
    @Autowired
    private lateinit var httpSession: HttpSession

    private val log: Log = LogFactory.getLog("HomeControllerLogger")

    @GetMapping("/")
    private fun home(model: Model): String {
        log.info("Accessed /")
        val user: SessionUser? = httpSession.getAttribute("user") as SessionUser?
        if (user != null) {
            model.addAttribute("userNickname", user.name)
            model.addAttribute("userEmail", user.email)
            model.addAttribute("userPicture", user.picture ?: "null")
            log.info("userNickname: ${user.name}")
            log.info("userEmail: ${user.email}")
            log.info("userPicture: ${user.picture ?: "null"}")
        }
        return "index"
    }

    @GetMapping("/guest")
    private fun index(model: Model): String {
        log.info("Accessed /guest")
        val user: SessionUser? = httpSession.getAttribute("user") as SessionUser?
        if (user != null) {
            model.addAttribute("userNickname", user.name)
            model.addAttribute("userEmail", user.email)
            model.addAttribute("userPicture", user.picture ?: "null")
            log.info("userNickname: ${user.name}")
            log.info("userEmail: ${user.email}")
            log.info("userPicture: ${user.picture ?: "null"}")
        }
        return "guest"
    }
}