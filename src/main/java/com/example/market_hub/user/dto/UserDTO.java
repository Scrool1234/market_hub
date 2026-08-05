package com.example.market_hub.user.dto;

import com.example.market_hub.user.entity.Role;
import lombok.Builder;

@Builder
public record UserDTO(
        String firstName,
        String lastName,
        String email,
        String phone,
        Role role) {
}
