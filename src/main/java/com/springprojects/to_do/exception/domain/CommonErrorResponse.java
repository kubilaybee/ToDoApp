package com.springprojects.to_do.exception.domain;

import java.util.List;

public class CommonErrorResponse {
    private List<CommonError> errors;

    public CommonErrorResponse(List<CommonError> errors) {
        this.errors = errors;
    }

    public CommonErrorResponse() {
    }

    public List<CommonError> getErrors() {
        return errors;
    }

    public void setErrors(List<CommonError> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "CommonErrorResponse{" +
                "errors=" + errors +
                '}';
    }
}
