package com.max.website.dto

import java.time.LocalDateTime
import java.util.*


data class BlogPostDto(
    var id: String? = null,
    val title: String,
    val content: String,
    val authorId: UUID,
    val authorName: String?,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)




