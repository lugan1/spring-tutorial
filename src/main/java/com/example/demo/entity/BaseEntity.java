package com.example.demo.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Getter
public abstract class BaseEntity {
    LocalDateTime createDate; /*생성 일시*/

    LocalDateTime updateDate; /*수정 일시*/
}
