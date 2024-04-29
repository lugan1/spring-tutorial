package com.example.demo.exception;

import com.example.demo.model.ErrorResponseDto;
import com.example.demo.model.FieldError;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.enumeration.ErrorCode.*;

@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler
    protected ResponseEntity<ErrorResponseDto> handleSignatureException(SignatureException e) {
        final ErrorResponseDto response = ErrorResponseDto.builder().code(INVALID_TOKEN.getCode()).build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException e) {
        final List<FieldError> fieldErrorList = e.getConstraintViolations()
                .stream()
                .map(error -> FieldError
                                .builder()
                                .field(error.getPropertyPath().toString())
                                .reason(error.getMessageTemplate())
                                .build()
                )
                .toList();

        final ErrorResponseDto response = ErrorResponseDto.builder()
                .code(INVALID_INPUT_VALUE.getCode())
                .message(INVALID_INPUT_VALUE.getMessage())
                .errors(fieldErrorList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
