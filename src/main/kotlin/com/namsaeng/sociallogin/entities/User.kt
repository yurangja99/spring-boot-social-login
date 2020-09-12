package com.namsaeng.sociallogin.entities

import javax.persistence.*

@Table(name="user")
@Entity
class User (
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        var id: Long = 0,
        @Column(name="name", nullable=false)
        var name: String,
        @Column(name="email", nullable=false)
        var email: String,
        @Column(name="picture")
        var picture: String?,
        @Column(name="role", nullable=false)
        var role: String,
        @Column(name="access_token", nullable=false)
        var accessToken: String
) {
    constructor (): this (name="name", email="email", picture=null, role="ROLE_GUEST", accessToken="access_token")
    fun update (name: String, picture: String?, accessToken: String): User {
        this.name = name
        this.picture = picture
        this.accessToken = accessToken
        return this
    }
    fun getRoleKey(): String {
        return this.role
    }
}