package com.max.website.model

import java.time.LocalDateTime
import javax.persistence.*

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
