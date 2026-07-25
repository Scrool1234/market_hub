package com.example.market_hub.service;

import com.example.market_hub.dto.auth.CredentialsDTO;
import com.example.market_hub.entity.User;
import com.example.market_hub.repository.UserRepository;
import com.example.market_hub.dto.auth.AuthDTO;
import com.example.market_hub.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public void register(AuthDTO authDTO) {

        User user = User.builder()
                .firstName(authDTO.getFirstName())
                .lastName(authDTO.getLastName())
                .email(authDTO.getEmail())
                .password(passwordEncoder.encode(authDTO.getPassword()))
                .role(null)
                .phone(authDTO.getPhone())
                .build();

        userRepository.save(user);
    }

    public AuthDTO login(CredentialsDTO credentialsDTO) {

        User user = userRepository.findByEmail(credentialsDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("User with email = %s not found", credentialsDTO.getEmail())
                ));

        if(passwordEncoder.matches(credentialsDTO.getPassword(), user.getPassword())) {
            return jwtService.generateAuthDTO();
        }

    }

    public void logout() {

    }

    public void refreshToken() {

    }
}
