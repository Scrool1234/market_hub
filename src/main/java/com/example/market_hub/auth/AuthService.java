package com.example.market_hub.auth;

import com.example.market_hub.auth.dto.CredentialsDTO;
import com.example.market_hub.auth.dto.RefreshTokenDTO;
import com.example.market_hub.exception.ResourceNotFoundException;
import com.example.market_hub.user.dto.CreateUserDTO;
import com.example.market_hub.user.dto.UserDTO;
import com.example.market_hub.user.entity.Role;
import com.example.market_hub.user.entity.RoleName;
import com.example.market_hub.user.entity.User;
import com.example.market_hub.exception.DuplicateResourceException;
import com.example.market_hub.user.UserMapper;
import com.example.market_hub.user.repository.RoleRepository;
import com.example.market_hub.user.repository.UserRepository;
import com.example.market_hub.auth.dto.AuthDTO;
import com.example.market_hub.auth.security.JwtService;
import com.example.market_hub.auth.security.SecurityUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO register(CreateUserDTO createUserDTO) {

        if(userRepository.existsByEmail(createUserDTO.email())) {
            throw new DuplicateResourceException("User with this email already exists");
        }

        Role role = roleRepository.findByName(RoleName.USER.name())
                .orElseThrow(() -> new ResourceNotFoundException("Role USER not found"));

        User user = User.builder()
                .firstName(createUserDTO.firstName())
                .lastName(createUserDTO.lastName())
                .email(createUserDTO.email())
                .password(passwordEncoder.encode(createUserDTO.password()))
                .phone(createUserDTO.phone())
                .role(role)
                .build();

        User savedUser = userRepository.save(user);
        log.info("User with id {} saved successfully", savedUser.getId());
        return userMapper.toUserDTO(savedUser);
    }

    public AuthDTO login(CredentialsDTO credentialsDTO) {
        User user = userRepository.findByEmail(credentialsDTO.email())
                .orElseThrow(() ->  new BadCredentialsException("Invalid username or password"));

        if(passwordEncoder.matches(credentialsDTO.password(), user.getPassword())) {
            return jwtService.generateAuthDTO(user.getEmail());
        }
        throw new BadCredentialsException("Invalid username or password");
    }

    public AuthDTO refreshToken(RefreshTokenDTO refreshTokenDTO) {
        return jwtService.refreshToken(refreshTokenDTO.refreshToken());
    }

    public SecurityUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated");
        }
        return (SecurityUserDetails) authentication.getPrincipal();
    }

}
