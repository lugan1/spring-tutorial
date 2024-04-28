package com.example.demo.model;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FieldError {
    private String field;
    private String value;
    private String reason;
}
