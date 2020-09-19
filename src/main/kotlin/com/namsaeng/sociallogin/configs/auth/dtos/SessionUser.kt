package com.namsaeng.sociallogin.configs.auth.dtos

import com.namsaeng.sociallogin.entities.User
import java.io.Serializable

class SessionUser (
        var id: Long,
        var name: String,
        var email: String,
        var picture: String?,
        var fbToken: String?
): Serializable {
    constructor (user: User): this (
            user.id, user.name, user.email, user.picture, user.fbToken
    )
}