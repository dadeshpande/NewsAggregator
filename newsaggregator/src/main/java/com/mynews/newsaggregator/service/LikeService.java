package com.mynews.newsaggregator.service;

import com.mynews.newsaggregator.entity.Likes;
import com.mynews.newsaggregator.exception.LikeNotFoundException;
import com.mynews.newsaggregator.repository.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepo likeRepo;


    public List<Likes> getAllLikes() {
        return likeRepo.findAll();
    }

    public Likes getLikesById(Long id) {
        return likeRepo.findById(id).orElse(null); // Example ID, replace with actual logic
    }

    public Likes addLike(Likes like) {
        return likeRepo.save(like); // Assuming a method to save likes exists
    }

    public Likes updateLike(Long id, Likes like) throws LikeNotFoundException {
        Optional<Likes> existingLike = likeRepo.findById(id);
        if(!existingLike.isPresent()) {
            throw new LikeNotFoundException("Like with ID " + id + " not found");
        }
        Likes updatedLike = existingLike.get();
        updatedLike.setUserId(like.getUserId());
        updatedLike.setNewsId(like.getNewsId());
        updatedLike.setTimestamp(like.getTimestamp());

        return likeRepo.save(updatedLike);
    }

    public ResponseEntity<String> deleteLike(Long id) throws LikeNotFoundException {
        if (!likeRepo.existsById(id)) {
            throw new LikeNotFoundException("Like with ID " + id + " not found");
        }
        likeRepo.deleteById(id);
        return ResponseEntity
                .status(200)
                .body("Like deleted successfully");
    }
}
