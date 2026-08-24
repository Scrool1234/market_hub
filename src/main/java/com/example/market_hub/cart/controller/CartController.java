package com.example.market_hub.cart.controller;

import com.example.market_hub.cart.service.CartService;
import com.example.market_hub.cart.dto.AddCartItemRequest;
import com.example.market_hub.cart.dto.CartResponse;
import com.example.market_hub.cart.dto.MergeGuestCartRequest;
import com.example.market_hub.cart.dto.UpdateCartItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<CartResponse> addCartItem(@RequestBody AddCartItemRequest addCartItemRequest) {
        return ResponseEntity.ok(cartService.addCartItem(addCartItemRequest));
    }

    @PatchMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> updateCartItem(@RequestBody UpdateCartItemRequest updateCartItemRequest,
                                                       @PathVariable Long itemId) {
        return ResponseEntity.ok(cartService.updateCartItem(updateCartItemRequest, itemId));
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> deleteCartItem(@PathVariable Long itemId) {
        cartService.deleteCartItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/merge")
    public ResponseEntity<CartResponse> merge(@RequestBody MergeGuestCartRequest mergeGuestCartRequest) {
        return ResponseEntity.ok(cartService.merge(mergeGuestCartRequest));
    }

    @DeleteMapping("/items")
    public ResponseEntity<Void> clear() {
        cartService.clear();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCurrentUserCart() {
        return ResponseEntity.ok(cartService.get());
    }

}
