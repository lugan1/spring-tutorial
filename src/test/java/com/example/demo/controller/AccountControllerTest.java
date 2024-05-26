package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginShouldReturnOkStatus() throws Exception {
        mockMvc.perform(post("/account/login"))
                .andExpect(status().isForbidden());
    }

    @Test
    void signUpShouldReturnOkStatus() throws Exception {
        mockMvc.perform(post("/account/sign-up"))
                .andExpect(status().isOk());
    }
}
