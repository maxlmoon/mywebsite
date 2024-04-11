package com.max.website.repository

import com.max.website.model.BlogPost
import com.max.website.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BlogPostRepository : JpaRepository<BlogPost, String> {
    fun findByAuthor(author: User): List<BlogPost>
}