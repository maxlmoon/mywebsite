package com.max.website.service

import com.max.website.dto.BlogPostDto
import com.max.website.model.BlogPost
import com.max.website.repository.BlogPostRepository
import org.springframework.stereotype.Service

@Service
class BlogPostService(private val blogPostRepository: BlogPostRepository) {

    fun findAllPosts(): List<BlogPost> = blogPostRepository.findAll()

    fun savePost(post: BlogPost): BlogPost = blogPostRepository.save(post)

    fun findPostById(id: String): BlogPost? = blogPostRepository.findById(id).orElse(null)

    fun deletePostById(id: String) = blogPostRepository.deleteById(id)

    fun convertToEntity(dto: BlogPostDto): BlogPost {
        return BlogPost(
            title = dto.title,
            content = dto.content,
            author = dto.author,
            // Only set ID and createdAt if non-null, otherwise let the database handle it
            id = dto.id,
            createdAt = dto.createdAt
        )
    }

    fun convertToDto(entity: BlogPost): BlogPostDto {
        return BlogPostDto(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            author = entity.author,
            createdAt = entity.createdAt
        )
    }


}
