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

    @Column(nullable = false, length = 100000)
    var content: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    var author: User,


    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "blogPost", cascade = [CascadeType.ALL], orphanRemoval = true)
    val likes: List<BlogPostLike> = mutableListOf()
)
