package com.namsaeng.sociallogin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SocialloginApplicationTests (
		@Autowired val restTemplate: TestRestTemplate
) {

	@Test
	fun loadMainPage() {
		val entity = restTemplate.getForEntity<String>("/")
		assertEquals(entity.statusCode, HttpStatus.OK)
		assertTrue(entity.body?.contains("<h1>스프링 부트 소셜 로그인 테스트</h1>") ?: false)
	}

}
