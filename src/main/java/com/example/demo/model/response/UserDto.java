package com.example.demo.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String role;
    private String status;
    private String createdDate;
    private String updatedDate;
}
