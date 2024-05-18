package com.example.demo.config.filter;

import com.example.demo.config.jwt.JWTUtil;
import com.example.demo.model.request.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    final ObjectMapper objectMapper;

    public JWTLoginFilter(
            AuthenticationManager authenticationManager,
            ObjectMapper objectMapper
    ) {
        super(authenticationManager);
        this.objectMapper = objectMapper;
        // 이 필터가 처리할 URL을 "/patient/login"으로 설정합니다.
        setFilterProcessesUrl("/patient/login");
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException
    {
        // 이 메서드는 로그인 요청이 있을 때 호출됩니다.
        // 요청에서 로그인 자격 증명을 읽어 사용자를 인증하려고 시도합니다.
        LoginDto login = objectMapper.readValue(request.getInputStream(), LoginDto.class);
        return new UsernamePasswordAuthenticationToken(login, null, null);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException
    {
        // 이 메서드는 인증이 성공했을 때 호출됩니다.
        // 인증된 사용자 정보를 요청 속성에 저장하고, JWT 토큰을 응답 헤더에 설정합니다.
        var patient = (LoginDto) authResult.getPrincipal();

        request.setAttribute("loginUser", patient);
        response.setHeader(HttpHeaders.AUTHORIZATION, JWTUtil.makeAuthToken(patient));
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 이 메서드는 인증이 실패했을 때 호출됩니다.
        // 실패 시나리오를 처리하며, 오류를 기록하거나 실패 응답을 설정할 수 있습니다.
        super.unsuccessfulAuthentication(request, response, failed);
    }
}

