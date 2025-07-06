package com.mynews.newsaggregator.exception;

public class NewsNotFoundException extends Exception {

    public NewsNotFoundException(String message) {
        super(message);
    }

    public NewsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
