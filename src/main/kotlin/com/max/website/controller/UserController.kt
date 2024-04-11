package com.max.website.controller

import com.max.website.model.User
import com.max.website.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.util.*

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


    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: UUID): ResponseEntity<User?> {
        val user = userService.findById(id)
        return user?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
    }
}
