package com.example.market_hub.exception.dto;

import lombok.Builder;

@Builder
public record ErrorDTO(
        Integer status,
        String error,
        String message) {
}
