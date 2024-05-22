package com.example.demo.model.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@NoArgsConstructor
@Setter
@Getter
public class LoginDto {
    String email;
    String password;
    String userAgent; /*접속 정보 (디바이스/브라우저)*/
}
