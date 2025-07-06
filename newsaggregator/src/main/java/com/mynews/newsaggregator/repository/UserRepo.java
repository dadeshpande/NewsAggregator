package com.mynews.newsaggregator.repository;

import com.mynews.newsaggregator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    // Additional query methods can be defined here if needed
}
