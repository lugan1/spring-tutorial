package com.example.demo.service;

import com.example.demo.model.request.PasswordResetDto;
import com.example.demo.model.response.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean resetPassword(PasswordResetDto passwordResetDto);
    List<UserDto> getAllMembers();
    Optional<UserDto> getMemberById(Long id);
}
