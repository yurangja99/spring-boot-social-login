package com.namsaeng.sociallogin.configs.auth.dtos

import com.namsaeng.sociallogin.entities.User

class OAuthAttributes(
        val attributes: Map<String, Any>,
        val nameAttributeKey: String,
        val name: String,
        val email: String,
        val picture: String?
) {
    companion object {
        // OAuth2User에서 반환하는 사용자 정보는 Map이므로 서비스에 맞게 변환해야함.
        fun of (
                registrationId: String,
                userNameAttributeName: String,
                attributes: Map<String, Any>
        ): OAuthAttributes {
            return ofGoogle(userNameAttributeName, attributes)
        }

        private fun ofGoogle(
                userNameAttributeName: String, attributes: Map<String, Any>
        ): OAuthAttributes {
            return OAuthAttributes(
                    name = attributes["name"]?.toString() ?: "",
                    email = attributes["email"]?.toString() ?: "",
                    picture = attributes["picture"]?.toString(),
                    attributes = attributes,
                    nameAttributeKey = userNameAttributeName
            )
        }
    }

    fun toEntity(): User {
        return User (
                name = name, email = email, picture = picture, role = "ROLE_GUEST"
        )
    }
}