package com.max.website.service

import com.max.website.dto.BlogPostDto
import com.max.website.model.BlogPost
import com.max.website.model.BlogPostLike
import com.max.website.model.User
import com.max.website.repository.BlogPostLikeRepository
import com.max.website.repository.BlogPostRepository
import com.max.website.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class BlogPostService(
    private val blogPostRepository: BlogPostRepository,
    private val blogPostLikeRepository: BlogPostLikeRepository,
    private val userRepository: UserRepository
) {

    fun findPostsByUserId(userId: UUID): List<BlogPost>? {
        val user = userRepository.findById(userId).orElseThrow { Exception("User not found") }
        return user?.let { blogPostRepository.findByAuthor(it) }
    }


    fun findAllPosts(): List<BlogPost> = blogPostRepository.findAll()

    fun savePost(post: BlogPost): BlogPost = blogPostRepository.save(post)

    fun findPostById(id: String): BlogPost? = blogPostRepository.findById(id).orElse(null)

    fun deletePostById(user: User, id: String) {
        val blogPost = blogPostRepository.findById(id).orElseThrow {
            Exception("Blog post not found")
        }

        // Check if the current user is the creator of the blog post
        // Compare using UUIDs
        if (blogPost.author.id != user.id) {
            throw Exception("Only the creator can delete this blog post")
        }

        // Proceed with deletion if the user is authorized
        blogPostRepository.deleteById(id)
    }


    fun likeBlogPost(userId: UUID, blogPostId: String) {
        // Check if the like already exists to prevent duplicate likes
        if (blogPostLikeRepository.existsByUserIdAndBlogPostId(userId, blogPostId)) {
            throw Exception("User has already liked this blog post")
        }

        val user = userRepository.findById(userId).orElse(null)
            ?: throw Exception("User not found")
        val blogPost = blogPostRepository.findById(blogPostId).orElse(null)
            ?: throw Exception("Blog post not found")

        val blogPostLike = BlogPostLike(user = user, blogPost = blogPost)
        blogPostLikeRepository.save(blogPostLike)
    }






}
