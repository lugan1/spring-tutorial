package com.example.demo.controller;

import com.example.demo.model.ResponseDto;
import com.example.demo.model.request.PasswordResetDto;
import com.example.demo.model.response.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<UserDto>>> getMembers() {
        List<UserDto> members = userService.getAllMembers();
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserDto> memberPage = new PageImpl<>(members, pageable, members.size());
        return ResponseEntity.ok(new ResponseDto<>(members));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        Optional<UserDto> member = userService.getMemberById(Long.parseLong(id));
        return ResponseEntity.of(member);
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<String> changePassword(
            @PathVariable("id") Long userId,
            @RequestBody PasswordResetDto request
    ) {
        boolean isUpdated = userService.resetPassword(request);
        return ResponseEntity.ok().body("Password updated");
    }
}
