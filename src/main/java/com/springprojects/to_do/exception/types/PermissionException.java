package com.springprojects.to_do.exception.types;

public class PermissionException extends BaseException {
    public PermissionException(String placeholder, String source, String messageId) {
        super(placeholder, source, messageId);
    }
}
