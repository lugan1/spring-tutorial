package com.example.demo.model.request;


import lombok.Value;

@Value
public class LoginDto {
    String email;
    String password;
    String userAgent; /*접속 정보 (디바이스/브라우저)*/
}
