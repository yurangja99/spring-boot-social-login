package com.namsaeng.sociallogin

import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class RestControllerTest {
    // ***********************************
    // MockMvc를 이용한 유닛 테스트
    // ***********************************

    @Autowired
    lateinit var mvc: MockMvc

    // 유저 정보 가져오기 테스트
    @Test
    @WithMockUser(roles = ["GUEST"])
    fun loadUsersList() {
        mvc.perform(get("/users"))
                .andExpect(status().isOk)
                .andExpect(content().string(Matchers.containsString("\"content\"")))
                .andExpect(content().string(Matchers.containsString("\"pageable\"")))
    }
}
