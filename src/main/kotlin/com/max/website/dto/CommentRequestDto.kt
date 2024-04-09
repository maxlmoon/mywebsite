package com.max.website.dto

import java.util.*

data class CommentRequestDto(
    val text: String,
    val blogPostId: String,
    val userId: UUID
)
