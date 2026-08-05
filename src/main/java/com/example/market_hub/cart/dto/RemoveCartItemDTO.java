package com.example.market_hub.cart.dto;

import javax.validation.constraints.Min;

public record RemoveCartItemDTO(
        @Min(0) Long quantity
) {
}
