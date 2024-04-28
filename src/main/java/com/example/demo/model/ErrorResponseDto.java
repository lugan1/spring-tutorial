package com.example.demo.model;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ErrorResponseDto {
    private String code;
    private String message;
    private List<FieldError> errors;
}
