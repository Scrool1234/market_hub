package com.example.market_hub.product.dto;

import lombok.Builder;

@Builder
public record ProductDTO(
        String name,
        String description) {
}
