package com.max.website.service

import com.max.website.model.Comment
import com.max.website.model.CommentLike
import com.max.website.repository.CommentLikeRepository
import com.max.website.repository.CommentRepository
import com.max.website.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val commentLikeRepository: CommentLikeRepository,
    private val userRepository: UserRepository
) {

    fun addComment(comment: Comment): Comment = commentRepository.save(comment)

    fun getCommentsByPostId(postId: String): List<Comment> = commentRepository.findByBlogPostId(postId)

    fun likeComment(commentId: Long): Comment {
        val comment = commentRepository.findById(commentId).orElseThrow { Exception("Comment not found") }
        comment.likes++
        return commentRepository.save(comment)
    }
    fun likeComment(userId: UUID, commentId: Long) {
        if (commentLikeRepository.existsByUserIdAndCommentId(userId, commentId)) {
            throw Exception("User has already liked this comment")
        }

        val comment = commentRepository.findById(commentId).orElseThrow { Exception("Comment not found") }
        val user = userRepository.findById(userId).orElseThrow { Exception("User not found") }

        val commentLike = user?.let { CommentLike(user = it, comment = comment) }
        if (commentLike != null) {
            commentLikeRepository.save(commentLike)
        }

        // Optionally, you can update the likes count in the Comment entity here,
        // if you decide to keep a redundant counter for performance reasons.
    }
}
