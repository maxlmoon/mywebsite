package com.max.website.repository

import com.max.website.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByBlogPostId(blogPostId: String): List<Comment>
}
