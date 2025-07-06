package com.mynews.newsaggregator.repository;

import com.mynews.newsaggregator.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository<News, Long> {
    // Additional query methods can be defined here if needed
}
