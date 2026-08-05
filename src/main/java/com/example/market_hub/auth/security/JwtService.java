package com.example.market_hub.auth.security;

import com.example.market_hub.auth.dto.AuthDTO;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JwtService {

    public AuthDTO generateAuthDTO(String email) {
        String accessToken = generateAccessToken();
        String refreshToken = generateRefreshToken();
        return new AuthDTO(accessToken, refreshToken);
    }

    public AuthDTO refreshToken(String refreshToken) {
        String accessToken = generateAccessToken();
        String newRefreshToken = generateRefreshToken();
        return new AuthDTO(accessToken, newRefreshToken);
    }

    public String generateAccessToken() {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 720000))
                .compact();
    }

    public String generateRefreshToken() {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 720000 * 24 * 7))
                .compact();
    }

    public void validateJwtToken(String token) {

    }

    public String getEmailFromToken(String token) {
        return null;
    }

}
