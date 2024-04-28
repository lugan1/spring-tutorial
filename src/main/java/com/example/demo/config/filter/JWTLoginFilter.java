package com.example.demo.config.filter;

import com.example.demo.config.jwt.JWTUtil;
import com.example.demo.model.request.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    final ObjectMapper objectMapper;

    public JWTLoginFilter(
            AuthenticationManager authenticationManager,
            ObjectMapper objectMapper
    ) {
        super(authenticationManager);
        this.objectMapper = objectMapper;
        setFilterProcessesUrl("/patient/login");
    }


    // login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException
    {
        LoginDto login = objectMapper.readValue(request.getInputStream(), LoginDto.class);

/*        Patient patient = patientRepository.findByIdAndStatus(login.getId(), ISOLATION)
                .orElse(null);*/
        //todo: 레포지토리로부터 사용자 정보를 가져오는 코드를 추가해야 합니다.

/*        if(patient != null) {
            return new UsernamePasswordAuthenticationToken(login, null, null);
        } else{
            throw new UsernameNotFoundException(String.valueOf(login.getId()));
        }*/

        return new UsernamePasswordAuthenticationToken(login, null, null);
    }

    // 인증이 성공했을 때 실행되는 함수, JWT를 발급해줘야 한다.
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException
    {
        var patient = (LoginDto) authResult.getPrincipal();

        request.setAttribute("loginUser", patient);
        response.setHeader(HttpHeaders.AUTHORIZATION, JWTUtil.makeAuthToken(patient));
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        chain.doFilter(request, response);
    }

    // 인증이 실패했을 때 실행되는 함수
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
