package com.example.market_hub.service;

import com.example.market_hub.dto.users.CreateUserDTO;
import com.example.market_hub.dto.users.UserDTO;
import com.example.market_hub.entity.User;
import com.example.market_hub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void getUsers() {

        /*
        List<UserDTO> users = userRepository.findAll().stream()
                .map()
                .collect(Collectors.toList());
         */

        // Нужно создать мапперы
    }

    public void createUser(CreateUserDTO createUserDTO) {

        User user = User.builder()
                .firstName(createUserDTO.getFirstName())
                .lastName(createUserDTO.getLastName())
                .email(createUserDTO.getEmail())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .phone(createUserDTO.getPhone())
                .build();

        userRepository.save(user);

    }

    public void deleteUser(Long userId) {

    }
}
