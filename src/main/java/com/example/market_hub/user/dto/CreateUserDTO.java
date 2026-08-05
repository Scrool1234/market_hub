package com.example.market_hub.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CreateUserDTO(
        @NotBlank(message = "Имя не должно быть пустым") String firstName,
        @NotBlank(message = "Фамилия не должна быть пустым") String lastName,
        @NotBlank(message = "Email не должен быть пустым") String email,
        @Size(min = 6, max = 13) String password,
        String phone) {

}
