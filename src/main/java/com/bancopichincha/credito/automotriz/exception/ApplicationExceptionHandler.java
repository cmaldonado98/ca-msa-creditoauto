package com.bancopichincha.credito.automotriz.exception;

import com.bancopichincha.credito.automotriz.model.dto.CommonResponseDto;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode.INVALID_PARAMETERS;
import static com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode.UNDEFINED_ERROR;
import static lombok.AccessLevel.PRIVATE;

@ControllerAdvice
@FieldDefaults(level = PRIVATE)
@Log4j2
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleHttpException(ApplicationException exception) {
        log.error(String.format("Application error: %s: %s", exception.getStatus().getCode(), exception.getStatus().getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(CommonResponseDto.build(exception.getStatus()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception) {
        log.error(String.format("Unexpected error: %s", exception.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(CommonResponseDto.builder()
                        .code(UNDEFINED_ERROR.getCode())
                        .message(String.format(UNDEFINED_ERROR.getMessage(), "ERROR VALOR DUPLICADO"))
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleHttpException(Exception exception) {
        log.error(String.format("Unexpected error: %s", exception.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(CommonResponseDto.builder()
                        .code(UNDEFINED_ERROR.getCode())
                        .message(String.format(UNDEFINED_ERROR.getMessage(), exception.getMessage()))
                        .build());
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(String.format("Unexpected error: %s",ex.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(HttpHeaders.CONTENT_TYPE, "application/json" )
                .body(CommonResponseDto.builder()
                        .code(INVALID_PARAMETERS.getCode())
                        .message(String.format(INVALID_PARAMETERS.getMessage(),ex.getMessage()))
                        .build());
    }




}
