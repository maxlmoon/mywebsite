package com.max.website.repository

import com.max.website.model.BlogPost
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BlogPostRepository : JpaRepository<BlogPost, Long> {
    // TODO define custom database queries
}

