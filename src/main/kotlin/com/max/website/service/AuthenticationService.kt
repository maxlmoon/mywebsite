package com.max.website.service


import com.max.website.dto.LoginUserDto
import com.max.website.dto.RegisterUserDto
import com.max.website.model.User
import com.max.website.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder
) {
    fun signup(input: RegisterUserDto): User {
        val user: User = User(
            fullName = input.fullName,
            email = input.email,
            userPassword = passwordEncoder.encode(input.password),
            roles = hashSetOf("ROLE_USER")
        )

        return userRepository.save(user)
    }

    fun authenticate(input: LoginUserDto): User? {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                input.email,
                input.password
            )
        )

        return userRepository.findByEmail(input.email)
            ?.orElseThrow { Exception("User not found") }
    }
}