package com.max.website.repository

import com.max.website.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
    // TODO define save, delete, find user queries
    // fun findByUsername(username: String): User? TODO method to query users based on fields like username
}
