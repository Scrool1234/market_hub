package com.example.market_hub.cart;

import com.example.market_hub.auth.AuthService;
import com.example.market_hub.cart.entity.Cart;
import com.example.market_hub.cart.dto.CartDTO;
import com.example.market_hub.cart.repository.CartItemRepository;
import com.example.market_hub.user.entity.User;
import com.example.market_hub.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;
    private final AuthService authService;
    private final CartItemRepository cartItemRepository;


    public void deleteCartItem(Long productId) {
        User user = authService.getCurrentUser().user();
        Cart cart = user.getCart();

        if(cart == null) {
            throw new ResourceNotFoundException("Cart not found");
        }

        cartItemRepository.deleteByCartIdAndProductId(cart.getId(), productId);
        log.info("CartItem deleted from cart with id {} successfully", cart.getId());
    }

    public void clear() {
        User user = authService.getCurrentUser().user();
        Cart cart = user.getCart();

        if(cart == null) {
            throw new ResourceNotFoundException("Cart not found");
        }

        cartItemRepository.deleteByCartId(cart.getId());
        log.info("Cart with id {} cleared successfully", cart.getId());
    }

    public CartDTO getCurrentUserCart() {
        User user = authService.getCurrentUser().user();
        Cart cart = user.getCart();

        if(cart == null) {
            throw new ResourceNotFoundException("Cart not found");
        }

        return cartMapper.toCartDTO(cart);
    }
}
