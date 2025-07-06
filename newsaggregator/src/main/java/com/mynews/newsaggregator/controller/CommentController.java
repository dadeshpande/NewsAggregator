package com.mynews.newsaggregator.controller;

import com.mynews.newsaggregator.entity.Comment;
import com.mynews.newsaggregator.exception.CommentNotFoundException;
import com.mynews.newsaggregator.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService; // Assuming a service layer exists for comments

    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/comments/{id}")
    public Comment getCommentsById(@RequestParam Long id) {
        return commentService.getCommentsById(id);
    }

    @PostMapping("/comments")
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment); // Assuming a method to add comments exists
    }

    @PutMapping("/comments/{id}")
    public Comment updateComment(@RequestParam Long id, @RequestBody Comment comment) throws CommentNotFoundException {
        return commentService.updateComment(id, comment); // Reusing addComment for update
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) throws CommentNotFoundException {
        return commentService.deleteComment(id); // Assuming a method to delete comments exists
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity handleCommentNotFound(CommentNotFoundException ex) {
        return ResponseEntity
                .status(404)
                .body("Comment not found: " + ex.getMessage());
    }
}
