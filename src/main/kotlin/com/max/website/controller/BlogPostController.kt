package com.max.website.controller

import com.max.website.dto.BlogPostDto
import com.max.website.service.BlogPostService
import com.max.website.dto.DtoConverter
import com.max.website.model.User
import com.max.website.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.UUID
@RestController
@RequestMapping("/api/blogposts")
class BlogPostController(
    private val blogPostService: BlogPostService,
    private val userService: UserService
) {

    @GetMapping
    fun getAllPosts(): ResponseEntity<List<BlogPostDto>> =
        ResponseEntity.ok(blogPostService.findAllPosts().map(DtoConverter::convertToDto))

    @PostMapping
    fun createPost(@RequestBody dto: BlogPostDto): ResponseEntity<BlogPostDto> {
        val author = dto.authorId.let { userService.findUserById(UUID.fromString(it.toString())) }


        val post = author?.let { DtoConverter.convertToEntity(dto, it) }
        val savedPost = post?.let { blogPostService.savePost(it) }
        return ResponseEntity.ok(savedPost?.let { DtoConverter.convertToDto(it) })
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: String): ResponseEntity<BlogPostDto> =
        blogPostService.findPostById(id)?.let { ResponseEntity.ok(DtoConverter.convertToDto(it)) }
            ?: ResponseEntity.notFound().build()

    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: String, @RequestBody dto: BlogPostDto): ResponseEntity<out Any> {
        val author = dto.authorId.let { userService.findUserById(UUID.fromString(it.toString())) }
        if (author == null) {
            // Handle the case where the author is not found
            return ResponseEntity.badRequest().body("Author not found")
        }

        return blogPostService.findPostById(id)?.let {
            val updatedPost = blogPostService.savePost(DtoConverter.convertToEntity(dto.copy(id = id), author))
            ResponseEntity.ok(DtoConverter.convertToDto(updatedPost))
        } ?: ResponseEntity.notFound().build()
    }

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

    @GetMapping("/user/{userId}")
    fun findBlogPostsByUserId(@PathVariable userId: UUID): ResponseEntity<List<BlogPostDto>> {
        val posts = blogPostService.findPostsByUserId(userId)?.map { post ->
            // Your method to convert BlogPost to BlogPostDto
            DtoConverter.convertToDto(post)
        }
        return ResponseEntity.ok(posts)
    }

}
