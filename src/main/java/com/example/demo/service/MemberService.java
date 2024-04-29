package com.example.demo.service;

import com.example.demo.model.request.PasswordResetDto;
import com.example.demo.model.response.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    boolean resetPassword(PasswordResetDto passwordResetDto);
    List<MemberDto> getAllMembers();
    Optional<MemberDto> getMemberById(Long id);
}
