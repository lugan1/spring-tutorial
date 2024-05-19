package com.example.demo.entity;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;


@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User extends BaseEntity implements UserDetails {

    Long id;

    String password;


    String name; /*격리자 이름*/

    @Builder.Default()
    private boolean enabled = true;

    /* 유저의 권한 목록, 권한 반환*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(UserAuthority.builder().build());
    }

    @Override
    public String getUsername() {
        return name;
    }

    /* 계정 만료 여부
     * true :  만료 안됨
     * false : 만료
     */
    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    /* 계정 잠김 여부
     * true : 잠기지 않음
     * false : 잠김
     */
    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    /* 비밀번호 만료 여부
     * true : 만료 안 됨
     * false : 만료
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
}
