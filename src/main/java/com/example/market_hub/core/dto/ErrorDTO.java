package com.example.market_hub.core.dto;

import lombok.Builder;

@Builder
public record ErrorDTO(
        Integer status,
        String error,
        String message) {
}
