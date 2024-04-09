package com.max.website.model

import jakarta.persistence.*

@Entity
@Table(name = "blog_post_likes", uniqueConstraints = [
    UniqueConstraint(columnNames = ["user_id", "blog_post_id"])
])
class BlogPostLike(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_post_id", nullable = false)
    val blogPost: BlogPost
)