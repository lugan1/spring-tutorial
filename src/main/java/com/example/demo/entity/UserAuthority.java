package com.example.demo.entity;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

@Builder
public class UserAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "ROLE_PATIENT";
    }
}