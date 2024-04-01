package com.max.website.service

import com.max.website.model.User
import com.max.website.repository.UserRepository
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) {
    fun allUsers(): List<User?> = userRepository.findAll().toList()
}