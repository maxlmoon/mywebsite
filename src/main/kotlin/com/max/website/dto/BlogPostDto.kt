package com.max.website.dto

import java.time.LocalDateTime


data class BlogPostDto(
    var id: String? = null,
    val title: String,
    val content: String,
    val author: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)




