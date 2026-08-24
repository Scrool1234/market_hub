package com.example.market_hub.cart.mapper;

import com.example.market_hub.cart.entity.Cart;
import com.example.market_hub.cart.dto.CartResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartResponse toCartResponse(Cart cart);
}
