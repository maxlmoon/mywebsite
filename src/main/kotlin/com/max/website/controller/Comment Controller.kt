package com.max.website.controller

import com.max.website.model.Comment
import com.max.website.model.User
import com.max.website.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/blogposts/{blogPostId}/comments")
class CommentController(private val commentService: CommentService) {

    @PostMapping
    fun addComment(@RequestBody comment: Comment, @PathVariable blogPostId: String): ResponseEntity<Comment> {
        val savedComment = commentService.addComment(comment)
        return ResponseEntity.ok(savedComment)
    }

    @GetMapping
    fun getComments(@PathVariable blogPostId: String): ResponseEntity<List<Comment>> {
        val comments = commentService.getCommentsByPostId(blogPostId)
        return ResponseEntity.ok(comments)
    }
}
