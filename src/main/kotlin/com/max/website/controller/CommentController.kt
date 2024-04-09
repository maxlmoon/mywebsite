package com.max.website.controller

import com.max.website.dto.CommentRequestDto
import com.max.website.model.Comment
import com.max.website.model.User
import com.max.website.repository.CommentLikeRepository
import com.max.website.repository.CommentRepository
import com.max.website.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/blogposts/{blogPostId}/comments")
class CommentController(
    private val commentService: CommentService,
    private val commentRepository: CommentRepository,
    private val commentLikeRepository: CommentLikeRepository
) {

    @PostMapping
    fun addComment(
        @RequestBody commentDto: CommentRequestDto,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<Comment> {
        val savedComment = commentService.addComment(commentDto.copy(userId = user.id))
        return ResponseEntity.ok(savedComment)
    }

    @GetMapping
    fun getComments(@PathVariable blogPostId: String): ResponseEntity<List<Comment>> {
        val comments = commentService.getCommentsByPostId(blogPostId)
        return ResponseEntity.ok(comments)
    }

    @PostMapping("/{commentId}/like")
    fun likeComment(@AuthenticationPrincipal user: User, @PathVariable commentId: Long,
                    @PathVariable blogPostId: String
    ): ResponseEntity<Void> {
        commentService.likeComment(user.id, commentId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @AuthenticationPrincipal user: User,
        @PathVariable blogPostId: String,
        @PathVariable commentId: Long
    ): ResponseEntity<Void> {
        // Call the service layer method for deleting a comment
        commentService.deleteComment(user.id, commentId)
        return ResponseEntity.ok().build()
    }


}
