package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BaseResponse <T> {
    private final String message = "success";
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final T data;

    public BaseResponse(T data) {
        this.data = data;
    }
}
