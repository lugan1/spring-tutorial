package com.example.demo.entity;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @DynamicInsert는 Hibernate에서 제공하는 어노테이션입니다.
 * 이 어노테이션은 엔티티를 데이터베이스에 삽입할 때 SQL 삽입문을 동적으로 생성하도록 지시합니다.
 * 기본적으로 Hibernate는 엔티티의 모든 필드를 포함하는 SQL Insert문을 생성합니다.
 * 그러나 @DynamicInsert가 적용된 엔티티에 대해서는, Hibernate는 null이 아닌 필드만 포함하는
 * SQL 삽입문을 생성합니다. 이렇게 하면 데이터베이스의 기본값을 활용할 수 있고,불필요한 데이터 전송을
 * 줄일 수 있습니다.
 *
 * **/

@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@ToString
public class Member extends BaseEntity implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 100, unique = true)
    String email;

    @Transient
    String password;

    @Column(nullable = false, length = 100)
    String name; /*격리자 이름*/

    @Builder.Default()
    private boolean enabled = true;

    /* 유저의 권한 목록, 권한 반환*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(MemberAuthority.builder().build());
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
