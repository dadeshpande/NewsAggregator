package com.mynews.newsaggregator.exception;

public class LikeNotFoundException extends Exception {

    public LikeNotFoundException(String message) {
        super(message);
    }

    public LikeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
