package com.example.demo.entity;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;


/**
 * MemberAuthority 클래스는 Spring Security의 GrantedAuthority 인터페이스를 구현합니다.
 * 이 클래스는 특정 사용자가 가진 권한을 나타냅니다.
 */
@Builder
public class MemberAuthority implements GrantedAuthority {

    /**
     * getAuthority 메서드는 사용자가 가진 권한을 문자열 형태로 반환합니다.
     * Spring Security에서 이 권한 문자열은 주로 "ROLE_" 접두사를 가지고 있으며,
     * 사용자의 역할을 나타냅니다.
     *
     * @return 사용자가 가진 권한을 나타내는 문자열 (예: "ROLE_USER").
     */
    @Override
    public String getAuthority() {
        return "ROLE_USER";
    }
}
