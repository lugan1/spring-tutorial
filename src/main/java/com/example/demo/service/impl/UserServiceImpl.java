package com.example.demo.service.impl;

import com.example.demo.model.request.PasswordResetDto;
import com.example.demo.model.response.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public boolean resetPassword(PasswordResetDto passwordResetDto) {
        return false;
    }

    @Override
    public List<UserDto> getAllMembers() {
        return List.of();
    }

    @Override
    public Optional<UserDto> getMemberById(Long id) {
        return Optional.empty();
    }
}
