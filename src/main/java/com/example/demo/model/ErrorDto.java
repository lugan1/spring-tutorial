package com.example.demo.model;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ErrorDto {
    private String code;
    private String message;
    private List<FieldErrorDto> errors;
}
