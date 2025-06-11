package com.assessment.exception;

public class TitleNotFoundException extends RuntimeException {
    public TitleNotFoundException(String title) {
        super("Title not found: " + title);
    }
}
