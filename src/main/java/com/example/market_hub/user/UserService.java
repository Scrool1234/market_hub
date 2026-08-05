package com.example.market_hub.user;

import com.example.market_hub.auth.AuthService;
import com.example.market_hub.user.dto.UpdateUserDTO;
import com.example.market_hub.user.dto.UserDTO;
import com.example.market_hub.user.entity.User;
import com.example.market_hub.exception.ResourceNotFoundException;
import com.example.market_hub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AuthService authenticationService;

    public UserDTO update(UpdateUserDTO updateUserDTO) {

        User user = authenticationService.getCurrentUser().user();

        if(updateUserDTO.firstName() != null) {
            user.setFirstName(updateUserDTO.firstName());
        }

        if(updateUserDTO.lastName() != null) {
            user.setLastName(updateUserDTO.lastName());
        }

        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        log.info("User with id {} updated successfully", savedUser.getId());
        return userMapper.toUserDTO(savedUser);
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toUserDTO(user);
    }

}
