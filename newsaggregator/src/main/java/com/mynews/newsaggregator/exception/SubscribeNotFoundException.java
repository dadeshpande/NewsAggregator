package com.mynews.newsaggregator.exception;

public class SubscribeNotFoundException extends Exception {

    public SubscribeNotFoundException(String message) {
        super(message);
    }

    public SubscribeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
