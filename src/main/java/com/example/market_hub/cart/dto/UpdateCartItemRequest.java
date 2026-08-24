package com.example.market_hub.cart.dto;

import javax.validation.constraints.Min;

public record UpdateCartItemRequest(
        @Min(1) Long quantity
) {
}
