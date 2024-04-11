package com.max.website.repository


import com.max.website.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<User?, UUID?> {
    fun findByEmail(email: String?): Optional<User?>?

}


// TODO define save, delete, find user queries
    // fun findByUsername(username: String): User? TODO method to query users based on fields like username

