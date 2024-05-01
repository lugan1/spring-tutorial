package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // 필드가 null인 경우 json 결과에서 제외됨
public class ResponseDto<T> {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final T data;
    private PagingDto paging;

    public ResponseDto(T data) {
        this.data = data;
    }

    private ResponseDto(T data, PagingDto paging) {
        assert paging != null;
        this.data = data;
        this.paging = paging;
    }

    public static <Type> ResponseDto<List<Type>> of(Page<Type> data) {
        assert data != null;
        return new ResponseDto<>(data.getContent(), PagingDto.of(data));
    }
}
