package com.max.website.controller

import com.max.website.model.User
import com.max.website.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(private val userService: UserService) {
    @GetMapping("/me")
    fun authenticatedUser(): ResponseEntity<User> {
        val authentication = SecurityContextHolder.getContext().authentication

        val currentUser = authentication.principal as User

        return ResponseEntity.ok(currentUser)
    }
    @GetMapping("/")
    fun allUsers(): ResponseEntity<List<User?>> {
        val users = userService.allUsers()

        return ResponseEntity.ok(users)
    }
}
