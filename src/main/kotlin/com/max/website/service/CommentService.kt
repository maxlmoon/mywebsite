package com.max.website.service

import com.max.website.model.Comment
import com.max.website.repository.CommentRepository
import org.springframework.stereotype.Service


@Service
class CommentService(private val commentRepository: CommentRepository) {

    fun addComment(comment: Comment): Comment = commentRepository.save(comment)

    fun getCommentsByPostId(postId: String): List<Comment> = commentRepository.findByBlogPostId(postId)
}

