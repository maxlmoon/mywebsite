package com.max.website.model

import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "comments")
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "comments_gen")
    @SequenceGenerator(name = "comments_gen", sequenceName = "comments_seq", allocationSize = 1)
    val id: Long = 0,


    @Column(nullable = false)
    val text: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_post_id", nullable = false)
    val blogPost: BlogPost,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Date? = null,

    @Column(nullable = false)
    var likes: Int = 0
)
