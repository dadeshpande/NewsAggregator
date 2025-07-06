package com.mynews.newsaggregator.controller;

import com.mynews.newsaggregator.entity.Likes;
import com.mynews.newsaggregator.exception.LikeNotFoundException;
import com.mynews.newsaggregator.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService; // Assuming a service layer exists for likes

    @GetMapping("/likes")
    public List<Likes> getAllLikes() {
        return likeService.getAllLikes();
    }

    @GetMapping("/likes/{id}")
    public Likes getLikesById(@RequestParam Long id) {
        return likeService.getLikesById(id);
    }

    @PostMapping("/likes")
    public Likes addLike(@RequestBody Likes like) {
        return likeService.addLike(like); // Assuming a method to add likes exists
    }

    @PutMapping("/likes/{id}")
    public Likes updateLike(@RequestParam Long id, @RequestBody Likes like) throws LikeNotFoundException {
        return likeService.updateLike(id, like); // Reusing addLike for update
    }

    @DeleteMapping("/likes/{id}")
    public ResponseEntity<String> deleteLike(@PathVariable Long id) throws LikeNotFoundException {
        return likeService.deleteLike(id); // Assuming a method to delete likes exists
    }

    @ExceptionHandler(LikeNotFoundException.class)
    public ResponseEntity handleLikeNotFound(LikeNotFoundException ex) {
        return ResponseEntity
                .status(404)
                .body("Like not found: " + ex.getMessage());
    }
}
