package com.springprojects.to_do.exception.domain;

public class CommonError {
    private int status;
    private String source;
    private String detail;

    public CommonError(int status, String source, String detail) {
        this.status = status;
        this.source = source;
        this.detail = detail;
    }

    public CommonError() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "CommonError{" +
                "status=" + status +
                ", source='" + source + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
