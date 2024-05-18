package com.example.demo.controller;

import com.example.demo.model.ResponseDto;
import com.example.demo.model.request.GatewayDto;
import com.example.demo.model.request.LoginDto;
import com.example.demo.model.response.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(
            @RequestBody LoginDto loginDto
    ) {
        LoginResponseDto login = new LoginResponseDto("token", 1234L);
        return ResponseEntity.ok().body(new ResponseDto<>(login));
    }

    @PostMapping("/gateway")
    ResponseEntity<Void> saveIotGatewayBiometric(
            @RequestBody GatewayDto gatewayDto
    ) {
        return ResponseEntity.ok().body(null);
    }
}
