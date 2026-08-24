package com.example.market_hub.cart.dto;

import javax.validation.constraints.Min;
import java.util.List;

public record MergeGuestCartRequest(
        List<MergeGuestCartItemDTO> mergeGuestCartItems
) {

    public record MergeGuestCartItemDTO(
            Long productVariantId,
            @Min(1) Long quantity
    ) {
    }
}
