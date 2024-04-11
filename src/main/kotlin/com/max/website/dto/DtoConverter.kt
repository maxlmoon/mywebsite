package com.max.website.dto


import com.max.website.model.BlogPost
import com.max.website.model.User
import java.util.*

object DtoConverter {

    fun convertToEntity(dto: BlogPostDto, author: User): BlogPost {
        return BlogPost(
            id = dto.id ?: UUID.randomUUID().toString(), // Generate new UUID if null
            title = dto.title,
            content = dto.content,
            author = author, // Directly use the User entity
            createdAt = dto.createdAt
        )
    }

    fun convertToDto(entity: BlogPost): BlogPostDto {
        return BlogPostDto(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            authorId = entity.author.id, // Get the author's ID from the User entity
            authorName = entity.author.fullName ?: "", // Get the author's full name, handle nulls appropriately
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )
    }
}
