package com.springprojects.to_do.exception.types;

public class BadRequestException extends BaseException {
    public BadRequestException(String placeholder, String source, String messageId) {
        super(placeholder, source, messageId);
    }
}
