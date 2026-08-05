package com.example.market_hub.cart;

import com.example.market_hub.cart.entity.Cart;
import com.example.market_hub.cart.dto.CartDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDTO toCartDTO(Cart cart);

}
