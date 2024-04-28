package com.example.demo.service.impl;

import com.example.demo.model.response.MemberDto;
import com.example.demo.model.request.PasswordResetDto;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
