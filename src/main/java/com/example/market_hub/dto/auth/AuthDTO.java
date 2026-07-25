package com.example.market_hub.dto.auth;

import lombok.Data;

@Data
public class AuthDTO {
    private String accessToken;
    private String refreshToken;
}
