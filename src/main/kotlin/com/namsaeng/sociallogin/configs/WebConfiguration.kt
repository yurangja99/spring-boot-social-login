package com.namsaeng.sociallogin.configs

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration: WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        // super.addCorsMappings(registry)
        // 여기에 CORS를 허락할 endpoint들과 host들을 정의한다.
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PATCH", "DELETE")
    }
}