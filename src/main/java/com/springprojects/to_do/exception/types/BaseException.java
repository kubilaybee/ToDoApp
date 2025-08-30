package com.springprojects.to_do.exception.types;

public class BaseException extends Exception {
    private String source;
    private String messageId;
    private Object[] placeholders;

    public BaseException(String placeholder, String source, String messageId) {
        super(messageId);
        this.source = source;
        this.messageId = messageId;
        this.placeholders = new Object[]{placeholder};
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setPlaceholders(Object[] placeholders) {
        this.placeholders = placeholders;
    }

    public String getSource() {
        return source;
    }

    public String getMessageId() {
        return messageId;
    }

    public Object[] getPlaceholders() {
        return placeholders;
    }
}
