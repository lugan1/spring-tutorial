package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class PagingDto {
    Integer page;
    Integer size;
    Long total;

    public static <Type> PagingDto of(Page<Type> page) {
        return PagingDto.builder()
                .page(page.getNumber())
                .total(page.getTotalElements())
                .size(page.getSize())
                .build();
    }
}
