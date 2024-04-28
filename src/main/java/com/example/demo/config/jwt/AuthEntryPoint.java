package com.example.demo.config.jwt;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
* AuthenticationEntryPoint는 Spring Security에서 제공하는 인터페이스로,
* 인증되지 않은 사용자가 보호된 리소스에 접근하려고 할 때 실행되는 메소드를 정의합니다.
* 예) 토큰이 없는 사용자가 API에 접근하려고 할 때 401 Unauthorized 에러를 반환합니다.
* 이 인터페이스를 구현한 클래스는 commence 메소드를 오버라이드하여 인증되지 않은 사용자가 보호된 리소스에 접근하려고 할 때의 동작을 정의합니다.
* */

@Component
@Slf4j
public class AuthEntryPoint implements AuthenticationEntryPoint {
 
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //todo : 인증되지 않은 사용자가 보호된 리소스에 접근하려고 할 때의 동작을 정의합니다.
        //예) 401 Unauthorized 에러를 반환합니다.
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
