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
        var role: String
) {
    constructor (): this (name="name", email="email", picture=null, role="ROLE_GUEST")
    fun update (name: String, picture: String?): User {
        this.name = name
        this.picture = picture;
        return this
    }
    fun getRoleKey(): String {
        return this.role
    }
}