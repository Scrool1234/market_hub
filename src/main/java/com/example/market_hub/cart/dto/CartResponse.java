package com.example.market_hub.cart.dto;

import com.example.market_hub.cart.entity.CartItem;
import com.example.market_hub.user.entity.User;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CartResponse(
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        User user,
        List<CartItem> cartItems) {
}
