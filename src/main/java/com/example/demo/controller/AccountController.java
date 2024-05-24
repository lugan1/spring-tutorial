package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @PostMapping("/login")
    public void login() {

    }


    @PostMapping("/sign-up")
    public void signUp() {

    }
}
