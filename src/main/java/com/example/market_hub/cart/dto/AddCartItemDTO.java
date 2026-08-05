package com.example.market_hub.cart.dto;

import javax.validation.constraints.Min;

public record AddCartItemDTO(
        Long productId,
        @Min(0) Long quantity
) {
}
