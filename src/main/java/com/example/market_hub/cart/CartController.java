package com.example.market_hub.cart;

import com.example.market_hub.cart.dto.AddCartItemDTO;
import com.example.market_hub.cart.dto.CartDTO;
import com.example.market_hub.cart.dto.RemoveCartItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<CartDTO> addCartItem() {
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/items/{productId}")
    public ResponseEntity<CartDTO> fff(@PathVariable Long productId) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<CartDTO> deleteCartItem(@PathVariable Long productId) {
        cartService.deleteCartItem(productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/items")
    public ResponseEntity<Void> clear() {
        cartService.clear();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CartDTO> getCurrentUserCart() {
        return ResponseEntity.ok(cartService.getCurrentUserCart());
    }

}
