package com.max.website.model

import jakarta.persistence.*

@Entity
@Table(name = "comment_likes")
class CommentLike(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    val comment: Comment
) {
    // Ensure that there's a unique constraint on user and comment combination
    // This can be done via @Table annotation or through database schema
}