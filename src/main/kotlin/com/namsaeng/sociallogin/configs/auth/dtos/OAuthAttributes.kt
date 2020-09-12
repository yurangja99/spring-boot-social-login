package com.namsaeng.sociallogin.configs.auth.dtos

import com.namsaeng.sociallogin.entities.User
import org.springframework.security.oauth2.core.OAuth2AccessToken

class OAuthAttributes(
        val attributes: Map<String, Any>,
        val nameAttributeKey: String,
        val name: String,
        val email: String,
        val picture: String?,
        val accessToken: OAuth2AccessToken
) {
    companion object {
        // OAuth2User에서 반환하는 사용자 정보는 Map이므로 서비스에 맞게 변환해야함.
        fun of (
                registrationId: String,
                userNameAttributeName: String,
                accessToken: OAuth2AccessToken,
                attributes: Map<String, Any>
        ): OAuthAttributes {
            when (registrationId) {
                "naver" -> return ofNaver("id", accessToken, attributes)
                "kakao" -> return ofKakao(userNameAttributeName, accessToken, attributes)
                else -> return ofGoogle(userNameAttributeName, accessToken, attributes)
            }
        }

        private fun ofGoogle(
                userNameAttributeName: String, accessToken: OAuth2AccessToken, attributes: Map<String, Any>
        ): OAuthAttributes {
            return OAuthAttributes(
                    name = attributes["name"]?.toString() ?: "",
                    email = attributes["email"]?.toString() ?: "",
                    picture = attributes["picture"]?.toString(),
                    attributes = attributes,
                    nameAttributeKey = userNameAttributeName,
                    accessToken = accessToken
            )
        }

        private fun ofNaver(
                userNameAttributeName: String, accessToken: OAuth2AccessToken, attributes: Map<String, Any>
        ): OAuthAttributes {
            val response: Map<String, Any> = attributes["response"] as Map<String, Any>
            return OAuthAttributes(
                    name = response["name"]?.toString() ?: "",
                    email = response["email"]?.toString() ?: "",
                    picture = response["profile_image"]?.toString(),
                    attributes = response,
                    nameAttributeKey = userNameAttributeName,
                    accessToken = accessToken
            )
        }

        private fun ofKakao(
                userNameAttributeName: String, accessToken: OAuth2AccessToken, attributes: Map<String, Any>
        ): OAuthAttributes {
            val kakaoAccount: Map<String, Any> = attributes["kakao_account"] as Map<String, Any>
            val profile: Map<String, Any> = kakaoAccount["profile"] as Map<String, Any>
            val attribute: Map<String, Any> = mapOf(
                    Pair("id", attributes["id"]?.toString() ?: ""),
                    Pair("name", profile["nickname"]?.toString() ?: ""),
                    Pair("email", kakaoAccount["email"]?.toString() ?: ""),
                    Pair("picture", profile["profile_image_url"]?.toString() ?: "")
            )
            return OAuthAttributes(
                    name = attribute["name"] as String,
                    email = attribute["email"] as String,
                    picture = attribute["picture"] as String,
                    attributes = attribute,
                    nameAttributeKey = userNameAttributeName,
                    accessToken = accessToken
            )
        }
    }

    fun toEntity(): User {
        return User (
                name = name, email = email, picture = picture, role = "ROLE_GUEST", accessToken = accessToken.tokenValue
        )
    }
}