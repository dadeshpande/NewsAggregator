package com.mynews.newsaggregator.exception;

public class CommentNotFoundException extends Exception {

    public CommentNotFoundException(String message) {
        super(message);
    }

    public CommentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
