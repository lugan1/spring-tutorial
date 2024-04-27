package com.example.demo.service;

import com.example.demo.model.MemberDto;
import com.example.demo.model.PasswordResetDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    boolean resetPassword(PasswordResetDto passwordResetDto);
    List<MemberDto> getAllMembers();
    Optional<MemberDto> getMemberById(Long id);
}
