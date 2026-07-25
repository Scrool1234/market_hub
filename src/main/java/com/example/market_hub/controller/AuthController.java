package com.example.market_hub.controller;

import com.example.market_hub.dto.auth.CredentialsDTO;
import com.example.market_hub.dto.auth.RefreshDTO;
import com.example.market_hub.dto.auth.AuthDTO;
import com.example.market_hub.dto.users.CreateUserDTO;
import com.example.market_hub.dto.users.UserDTO;
import com.example.market_hub.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserDTO register(@RequestBody CreateUserDTO createUserDTO) {

        return null;
    }

    @PostMapping("/login")
    public AuthDTO login(@RequestBody CredentialsDTO credentialsDTO) {

        return null;
    }

    @PostMapping("/logout")
    public void logout() {

    }

    @PostMapping("/refresh")
    public AuthDTO refresh(@RequestBody RefreshDTO refreshDTO) {

        return null;
    }

}
