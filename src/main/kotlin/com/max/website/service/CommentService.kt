package com.max.website.service

import com.max.website.dto.CommentRequestDto
import com.max.website.model.Comment
import com.max.website.model.CommentLike
import com.max.website.repository.BlogPostRepository
import com.max.website.repository.CommentLikeRepository
import com.max.website.repository.CommentRepository
import com.max.website.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*


@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val commentLikeRepository: CommentLikeRepository,
    private val userRepository: UserRepository,
    private val blogPostRepository: BlogPostRepository
) {

    fun addComment(commentDto: CommentRequestDto): Comment? {
        val blogPost = blogPostRepository.findById(UUID.fromString(commentDto.blogPostId).toString())
            .orElseThrow { Exception("BlogPost not found") }
        val user = userRepository.findById(commentDto.userId)
            .orElseThrow { Exception("User not found") }

        val comment = user?.let {
            Comment(
                text = commentDto.text,
                blogPost = blogPost,
                user = it
            )
        }

        return comment?.let { commentRepository.save(it) }
    }


    fun getCommentsByPostId(postId: String): List<Comment> = commentRepository.findByBlogPostId(postId)

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

    @Transactional
    fun deleteComment(userId: UUID, commentId: Long) {
        val comment = commentRepository.findById(commentId).orElseThrow { Exception("Comment not found") }

        // Check if the requesting user is the author of the comment or has admin rights
        if (comment.user.id != userId /* && !user.hasRole("ADMIN") */) {
            throw Exception("Unauthorized to delete this comment")
        }

        // Delete all likes associated with the comment before deleting the comment itself
        commentLikeRepository.deleteByCommentId(commentId)

        // Finally, delete the comment
        commentRepository.deleteById(commentId)
    }
}

