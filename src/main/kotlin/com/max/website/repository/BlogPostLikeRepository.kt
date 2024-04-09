package com.max.website.repository


import com.max.website.model.BlogPostLike
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BlogPostLikeRepository : JpaRepository<BlogPostLike, Long> {
    fun existsByUserIdAndBlogPostId(userId: UUID, blogPostId: String): Boolean
}


