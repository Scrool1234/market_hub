package com.example.market_hub.auth;

import com.example.market_hub.auth.dto.CredentialsDTO;
import com.example.market_hub.auth.dto.RefreshTokenDTO;
import com.example.market_hub.auth.dto.AuthDTO;
import com.example.market_hub.user.dto.CreateUserDTO;
import com.example.market_hub.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(createUserDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@Valid @RequestBody CredentialsDTO credentialsDTO) {
        return ResponseEntity.ok(authService.login(credentialsDTO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthDTO> refreshToken(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenDTO));
    }

}
