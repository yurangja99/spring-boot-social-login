package com.namsaeng.sociallogin.configs.auth.dtos

import com.namsaeng.sociallogin.entities.User
import java.io.Serializable

class SessionUser (
        var name: String,
        var email: String,
        var picture: String?
): Serializable {
    constructor (user: User): this (
            user.name, user.email, user.picture
    )
}