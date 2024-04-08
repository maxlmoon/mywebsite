package com.max.website.model

import jakarta.persistence.*
import jakarta.persistence.Id
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*


@Entity
@Table(name = "blog_posts")
data class BlogPost(


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false, length = 10000)
    var content: String,

    @Column(nullable = false)
    var author: String,

    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Date? = null
)
