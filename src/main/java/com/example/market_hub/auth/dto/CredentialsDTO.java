package com.example.market_hub.auth.dto;


import javax.validation.constraints.NotBlank;

public record CredentialsDTO(
        @NotBlank(message = "Email не может быть пустым") String email,
        @NotBlank(message = "Пароль не может быть пустым") String password) {
}
