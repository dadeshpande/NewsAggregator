package com.mynews.newsaggregator.repository;

import com.mynews.newsaggregator.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepo extends JpaRepository<Likes, Long> {
    // Additional query methods can be defined here if needed
}
