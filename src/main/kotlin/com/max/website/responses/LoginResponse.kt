package com.max.website.responses


class LoginResponse {
    var token: String? = null
    var expiresIn: Long = 0

    fun setToken(token: String?): LoginResponse {
        this.token = token
        return this
    }

    fun setExpiresIn(expiresIn: Long): LoginResponse {
        this.expiresIn = expiresIn
        return this
    }
}