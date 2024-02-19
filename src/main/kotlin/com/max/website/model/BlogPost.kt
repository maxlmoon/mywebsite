package com.max.website.model

import jakarta.persistence.*
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
@Table(name = "blog_posts")
data class BlogPost(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false, length = 10000)
    var content: String,

    @Column(nullable = false)
    var author: String,

    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
)
