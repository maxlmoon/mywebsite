package com.max.website.controller

import com.max.website.dto.BlogPostDto
import com.max.website.service.BlogPostService
import com.max.website.dto.DtoConverter
import com.max.website.model.User
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.UUID
@RestController
@RequestMapping("/api/blogposts")
class BlogPostController(private val blogPostService: BlogPostService) {

    @GetMapping
    fun getAllPosts(): ResponseEntity<List<BlogPostDto>> =
        ResponseEntity.ok(blogPostService.findAllPosts().map(DtoConverter::convertToDto))

    @PostMapping
    fun createPost(@RequestBody dto: BlogPostDto): ResponseEntity<BlogPostDto> {
        dto.id = UUID.randomUUID().toString()
        val post = DtoConverter.convertToEntity(dto)
        post.createdAt = LocalDateTime.now()
        val savedPost = blogPostService.savePost(post)
        return ResponseEntity.ok(DtoConverter.convertToDto(savedPost))
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: String): ResponseEntity<BlogPostDto> =
        blogPostService.findPostById(id)?.let { ResponseEntity.ok(DtoConverter.convertToDto(it)) }
            ?: ResponseEntity.notFound().build()

    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: String, @RequestBody dto: BlogPostDto): ResponseEntity<BlogPostDto> =
        blogPostService.findPostById(id)?.let {
            val updatedPost = blogPostService.savePost(DtoConverter.convertToEntity(dto.copy(id = id)))
            ResponseEntity.ok(DtoConverter.convertToDto(updatedPost))
        } ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun deletePostById(
        @AuthenticationPrincipal user: User,
        @PathVariable id: String
    ): ResponseEntity<Void> {
        blogPostService.deletePostById(user, id) // Pass the authenticated user along with the blog post ID
        return ResponseEntity.ok().build()
    }

    @PostMapping("/{postId}/like")
    fun likeBlogPost(@AuthenticationPrincipal user: User, @PathVariable postId: String): ResponseEntity<Void> {
        blogPostService.likeBlogPost(user.id, postId)
        return ResponseEntity.ok().build()
    }
}
