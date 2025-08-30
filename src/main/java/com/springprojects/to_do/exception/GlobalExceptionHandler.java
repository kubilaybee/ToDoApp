package com.springprojects.to_do.exception;

import com.springprojects.to_do.exception.domain.CommonError;
import com.springprojects.to_do.exception.domain.CommonErrorResponse;
import com.springprojects.to_do.exception.types.BaseException;
import com.springprojects.to_do.exception.types.DataNotFoundException;
import com.springprojects.to_do.exception.types.PermissionException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(PermissionException.class)
    public ResponseEntity<CommonErrorResponse> handlePermissionException(PermissionException exception) {
        return new ResponseEntity<>(
                buildCommonErrorResponse(HttpStatus.FORBIDDEN, exception),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<CommonErrorResponse> handleDataNotFoundException(DataNotFoundException exception) {
        return new ResponseEntity<>(
                buildCommonErrorResponse(HttpStatus.NOT_FOUND, exception),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonErrorResponse> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        List<CommonError> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> {
                    CommonError commonError = new CommonError();
                    commonError.setStatus(HttpStatus.BAD_REQUEST.value());
                    commonError.setSource(error.getField());
                    commonError.setDetail(error.getDefaultMessage());
                    return commonError;
                })
                .collect(Collectors.toList());

        CommonErrorResponse response = new CommonErrorResponse();
        response.setErrors(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private CommonErrorResponse buildCommonErrorResponse(HttpStatus status, BaseException exception) {
        CommonError commonError = new CommonError();
        commonError.setStatus(status.value());
        commonError.setSource(exception.getSource());

        String errorMessage;
        try {
            errorMessage = messageSource.getMessage(
                    exception.getMessageId(),
                    exception.getPlaceholders(),
                    Locale.ENGLISH
            );
        } catch (Exception ex) {
            errorMessage = "Error message not found for ID: " + exception.getMessageId();
        }

        commonError.setDetail(errorMessage);

        CommonErrorResponse commonErrorResponse = new CommonErrorResponse();
        commonErrorResponse.setErrors(Collections.singletonList(commonError));
        return commonErrorResponse;
    }
}
