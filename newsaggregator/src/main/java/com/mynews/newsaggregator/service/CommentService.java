package com.mynews.newsaggregator.service;

import com.mynews.newsaggregator.entity.Comment;
import com.mynews.newsaggregator.exception.CommentNotFoundException;
import com.mynews.newsaggregator.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;


    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    public Comment getCommentsById(Long id) {
        return commentRepo.findById(id).orElse(null); // Example ID, replace with actual logic
    }

    public Comment addComment(Comment comment) {
        return commentRepo.save(comment); // Assuming a method to save comments exists
    }

    public Comment updateComment(Long id, Comment comment) throws CommentNotFoundException {
        Optional<Comment> existingComment = commentRepo.findById(id);
        if(!existingComment.isPresent()) {
            throw new CommentNotFoundException("Comment with ID " + id + " not found");
        }
        Comment updatedComment = existingComment.get();
        updatedComment.setContent(comment.getContent());
        updatedComment.setUserId(comment.getUserId());
        updatedComment.setLikes(comment.getLikes());
        updatedComment.setReplies(comment.getReplies());
        updatedComment.setNewsId(comment.getNewsId());
        updatedComment.setTimestamp(comment.getTimestamp());

        return commentRepo.save(updatedComment);
    }

    public ResponseEntity<String> deleteComment(Long id) throws CommentNotFoundException {
        if (!commentRepo.existsById(id)) {
            throw new CommentNotFoundException("Comment with ID " + id + " not found");
        }
        commentRepo.deleteById(id);
        return ResponseEntity
                .status(200)
                .body("Comment deleted successfully");
    }
}
