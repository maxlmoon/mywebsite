package com.max.website.repository

import com.max.website.model.CommentLike
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CommentLikeRepository : JpaRepository<CommentLike, Long> {
    fun existsByUserIdAndCommentId(userId: UUID, commentId: Long): Boolean
    fun deleteByCommentId(commentId: Long)
}
