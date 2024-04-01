package com.max.website.controller

import com.max.website.dto.LoginUserDto
import com.max.website.dto.RegisterUserDto
import com.max.website.model.User
import com.max.website.responses.LoginResponse
import com.max.website.service.AuthenticationService
import com.max.website.service.JwtService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthenticationController(
    private val jwtService: JwtService,
    private val authenticationService: AuthenticationService
) {
    @PostMapping("/signup")
    fun register(@RequestBody registerUserDto: RegisterUserDto?): ResponseEntity<User> {
        val registeredUser: User = authenticationService.signup(registerUserDto!!)

        return ResponseEntity.ok(registeredUser)
    }

    @PostMapping("/login")
    fun authenticate(@RequestBody loginUserDto: LoginUserDto?): ResponseEntity<LoginResponse> {
        val authenticatedUser: User? = authenticationService.authenticate(loginUserDto!!)

        val jwtToken = authenticatedUser?.let { jwtService.generateToken(it) }

        val loginResponse: LoginResponse = LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.expirationTime)

        return ResponseEntity.ok(loginResponse)
    }
}