package com.example.market_hub.cart.repository;

import com.example.market_hub.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteByCartId(Long cartId);
    void deleteByCartIdAndCartItemId(Long cartId, Long productId);
    Optional<CartItem> findByCartIdAndProductVariantId(Long cartId, Long productId);
    List<CartItem> findAllByCartId(Long cartId);
    Optional<CartItem> findByIdAndCartId(Long cartItemId, Long cartId);
}
