package com.mynews.newsaggregator.repository;

import com.mynews.newsaggregator.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    // Additional query methods can be defined here if needed
}
