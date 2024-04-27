package com.example.demo.service.impl;

import com.example.demo.model.MemberDto;
import com.example.demo.model.PasswordResetDto;
import com.example.demo.service.MemberService;

import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {
    @Override
    public boolean resetPassword(PasswordResetDto passwordResetDto) {
        return false;
    }

    @Override
    public List<MemberDto> getAllMembers() {
        return List.of();
    }

    @Override
    public Optional<MemberDto> getMemberById(Long id) {
        return Optional.empty();
    }
}
