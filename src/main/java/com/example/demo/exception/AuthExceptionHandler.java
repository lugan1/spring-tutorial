package com.example.demo.exception;

import com.example.demo.model.ErrorDto;
import com.example.demo.model.FieldErrorDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;
import java.util.List;

import static com.example.demo.enumeration.ErrorCode.INVALID_INPUT_VALUE;
import static com.example.demo.enumeration.ErrorCode.INVALID_TOKEN;

@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler
    protected ResponseEntity<ErrorDto> handleSignatureException(SignatureException e) {
        final ErrorDto response = ErrorDto.builder().code(INVALID_TOKEN.getCode()).build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException e) {
        final List<FieldErrorDto> fieldErrorDtoList = e.getConstraintViolations()
                .stream()
                .map(error -> FieldErrorDto
                                .builder()
                                .field(error.getPropertyPath().toString())
                                .reason(error.getMessageTemplate())
                                .build()
                )
                .toList();

        final ErrorDto response = ErrorDto.builder()
                .code(INVALID_INPUT_VALUE.getCode())
                .message(INVALID_INPUT_VALUE.getMessage())
                .errors(fieldErrorDtoList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
