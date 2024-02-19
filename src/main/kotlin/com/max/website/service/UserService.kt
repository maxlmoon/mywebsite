package com.max.website.service

import com.max.website.model.User
import com.max.website.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    fun findById(id: UUID): User? = userRepository.findById(id).orElse(null)

    fun saveUser(user: User): User = userRepository.save(user)

    // TODO registration, profile updates, querying user information
}