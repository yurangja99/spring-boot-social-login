package com.namsaeng.sociallogin.configs.auth

import com.namsaeng.sociallogin.configs.auth.dtos.OAuthAttributes
import com.namsaeng.sociallogin.configs.auth.dtos.SessionUser
import com.namsaeng.sociallogin.entities.User
import com.namsaeng.sociallogin.repositories.UserRepository
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.OAuth2AccessToken
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpSession

@Service
class CustomOAuth2UserService (
        val userRepository: UserRepository,
        val httpSession: HttpSession
): OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private val log: Log = LogFactory.getLog("OAuth2UserServiceLogger")

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val delegate: OAuth2UserService<OAuth2UserRequest, OAuth2User> =
                DefaultOAuth2UserService()
        val oAuth2User: OAuth2User = delegate.loadUser(userRequest)
        // 현재 로그인 중인 서비스를 구분하는 코드. 구글, 네이버 등 여러 서비스를 이용할 때 사용.
        val registrationId: String = userRequest.clientRegistration.registrationId
        // OAuth2 로그인 시 키가 되는 필드값. 여러 서비스를 이용할 때 사용
        val userNameAttributeName: String = userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName
        // Access Token 객체를 얻어 낸다. 토큰, 만료 시각 등이 들어 있다.
        val accessToken: OAuth2AccessToken = userRequest.accessToken
        // 사용자의 attribute 담을 클래스
        val attributes: OAuthAttributes = OAuthAttributes.of(
                registrationId,
                userNameAttributeName,
                accessToken,
                oAuth2User.attributes
        )
        val user: User = saveOrUpdate(attributes)
        // 세션에 사용자 정보를 저장할 때 필요한 DTO 클래스 (직렬화 지원)
        httpSession.setAttribute("user", SessionUser(user))
        return DefaultOAuth2User (
                Collections.singleton(SimpleGrantedAuthority(user.getRoleKey())),
                attributes.attributes,
                attributes.nameAttributeKey
        )
    }

    private fun saveOrUpdate (attributes: OAuthAttributes): User {
        log.info("Login with")
        log.info("userName: ${attributes.name}")
        log.info("userEmail: ${attributes.email}")
        log.info("userPicture: ${attributes.picture}")
        log.info("userToken: ${attributes.accessToken.tokenValue}")
        log.info("userTokenExpire: ${attributes.accessToken.expiresAt}")
        // 처음 가입 시, user가 null이 되어 attributes.toEntity()가 실행됨.
        val user: User = userRepository.findByEmail(attributes.email)
                ?: attributes.toEntity()
        return userRepository.save(user.update(attributes.name, attributes.picture, attributes.accessToken.tokenValue))
    }
}