package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseResponse <T> {
    private String message;
    private T data;
    private LocalDateTime timestamp;
}
