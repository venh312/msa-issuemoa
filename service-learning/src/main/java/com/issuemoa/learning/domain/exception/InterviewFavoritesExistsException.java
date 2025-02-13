package com.issuemoa.learning.domain.exception;

public class InterviewFavoritesExistsException extends RuntimeException{
    public InterviewFavoritesExistsException(String message) {
        super(message);
    }
}
