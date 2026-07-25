package com.example.market_hub.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @GetMapping("/{cartId}")
    public void getCart(@PathVariable Long cartId) {

    }

    @PostMapping("/")
    public void addToCart() {

    }

    @DeleteMapping("/")
    public void deleteFromCart() {

    }
}
