package com.example.demo.controller;

import com.example.demo.model.MemberDto;
import com.example.demo.model.PasswordResetDto;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    final MemberService memberService;


    @GetMapping
    public ResponseEntity<List<MemberDto>> getMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<MemberDto>> getUser(@PathVariable String id) {
        Optional<MemberDto> member = memberService.getMemberById(Long.parseLong(id));
        return ResponseEntity.ofNullable(member);
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<String> changePassword(
            @PathVariable("id") Long userId,
            @RequestBody PasswordResetDto request
    ) {
        boolean isUpdated = memberService.resetPassword(request);
        return ResponseEntity.ok().body("Password updated");
    }
}
