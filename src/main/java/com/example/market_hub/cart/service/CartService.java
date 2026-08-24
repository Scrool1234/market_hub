package com.example.market_hub.cart.service;

import com.example.market_hub.auth.AuthService;
import com.example.market_hub.cart.dto.AddCartItemRequest;
import com.example.market_hub.cart.dto.MergeGuestCartRequest;
import com.example.market_hub.cart.dto.UpdateCartItemRequest;
import com.example.market_hub.cart.entity.Cart;
import com.example.market_hub.cart.dto.CartResponse;
import com.example.market_hub.cart.entity.CartItem;
import com.example.market_hub.cart.mapper.CartMapper;
import com.example.market_hub.cart.repository.CartItemRepository;
import com.example.market_hub.cart.repository.CartRepository;
import com.example.market_hub.catalog.entity.ProductVariant;
import com.example.market_hub.catalog.repository.ProductVariantRepository;
import com.example.market_hub.user.entity.User;
import com.example.market_hub.core.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;
    private final AuthService authService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductVariantRepository productVariantRepository;

    public CartResponse addCartItem(AddCartItemRequest addCartItemRequest) {
        Cart cart = getOrCreateCart();

        ProductVariant productVariant = productVariantRepository.findById(addCartItemRequest.productVariantId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Product variant with id %d not found", addCartItemRequest.productVariantId())
                ));

        CartItem cartItem = cartItemRepository.findByCartIdAndProductVariantId(
                        cart.getId(),
                        addCartItemRequest.productVariantId()
                )
                .orElse(null);

        if(cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem = CartItem.builder()
                    .productVariant(productVariant)
                    .cart(cart)
                    .quantity(1L)
                    .build();
        }

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        log.info("Cart item saved from cart with id {} successfully", cart.getId());
        return cartMapper.toCartResponse(savedCartItem.getCart());
    }

    public CartResponse updateCartItem(UpdateCartItemRequest updateCartItemRequest,
                                       Long itemId) {
        Cart cart = getCurrentUserCart();

        CartItem cartItem = cartItemRepository
                .findByIdAndCartId(itemId, cart.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Cart item with id %d not found", itemId)
                ));

        cartItem.setQuantity(updateCartItemRequest.quantity());

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        log.info("Cart item updated from cart with id {} successfully", cart.getId());
        return cartMapper.toCartResponse(savedCartItem.getCart());
    }

    public void deleteCartItem(Long itemId) {
        Cart cart = getCurrentUserCart();
        cartItemRepository.deleteByCartIdAndCartItemId(cart.getId(), itemId);
        log.info("CartItem deleted from cart with id {} successfully", cart.getId());
    }

    public CartResponse merge(MergeGuestCartRequest mergeGuestCartRequest) {
        Cart cart = getOrCreateCart();

        Map<Long, Long> guestItems  = mergeGuestCartRequest.mergeGuestCartItems().stream()
                        .collect(Collectors.toMap(
                                MergeGuestCartRequest.MergeGuestCartItemDTO::productVariantId,
                                MergeGuestCartRequest.MergeGuestCartItemDTO::quantity
                        ));

        Map<Long, ProductVariant> productsVariantsById  = productVariantRepository.findAllById(guestItems.keySet()).stream()
                .collect(Collectors.toMap(
                        ProductVariant::getId,
                        Function.identity()
                ));

        if (productsVariantsById.size() != guestItems.size()) {
            throw new ResourceNotFoundException("Some products were not found");
        }

        Map<Long, CartItem> cartItemsByProductId = cartItemRepository.findAllByCartId(cart.getId()).stream()
                .collect(Collectors.toMap(
                        cartItem -> cartItem.getProductVariant().getId(),
                        Function.identity()
                ));

        guestItems.forEach((productId, quantity) -> {

            if(cartItemsByProductId.containsKey(productId)) {
                CartItem cartItem = cartItemsByProductId.get(productId);
                cartItem.setQuantity(
                        cartItem.getQuantity() + quantity
                );
            } else {

                CartItem cartItem = CartItem.builder()
                        .productVariant(productsVariantsById.get(productId))
                        .cart(cart)
                        .quantity(quantity)
                        .build();

                cartItemsByProductId.put(productId, cartItem);
            }

        });

        cartItemRepository.saveAll(cartItemsByProductId.values());
        log.info("Guest and authentication user carts merged successfully");
        return cartMapper.toCartResponse(cart);
    }

    public void clear() {
        Cart cart = getCurrentUserCart();
        cartItemRepository.deleteByCartId(cart.getId());
        log.info("Cart with id {} cleared successfully", cart.getId());
    }

    public CartResponse get() {
        return cartMapper.toCartResponse(getCurrentUserCart());
    }

    public Cart getCurrentUserCart() {
        User user = authService.getCurrentUser().user();
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Cart for user with id %d not found", user.getId())
                ));
        return cart;
    }

    private Cart getOrCreateCart() {
        User user = authService.getCurrentUser().user();
        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart cart = Cart.builder()
                            .user(user)
                            .build();
                    return cartRepository.save(cart);
                });
    }
}
