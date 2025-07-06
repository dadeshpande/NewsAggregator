package com.mynews.newsaggregator.repository;

import com.mynews.newsaggregator.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepo extends JpaRepository<Subscribe, Long> {
    // Custom query methods can be defined here if needed
}
