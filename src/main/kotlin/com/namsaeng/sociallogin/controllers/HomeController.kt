package com.namsaeng.sociallogin.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping("/")
    fun home(): String {
        return "index"
    }

    @GetMapping("/index")
    fun index(): String {
        return "index"
    }
}