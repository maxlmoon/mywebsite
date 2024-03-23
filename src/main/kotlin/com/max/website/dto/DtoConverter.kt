package com.max.website.dto


import com.max.website.model.BlogPost

object DtoConverter {

    fun convertToEntity(dto: BlogPostDto): BlogPost {
        return BlogPost(
            id = dto.id!!,
            title = dto.title,
            content = dto.content,
            author = dto.author,
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
