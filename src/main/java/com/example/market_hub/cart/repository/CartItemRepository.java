package com.example.market_hub.cart.repository;

import com.example.market_hub.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    void deleteByCartId(Long cartId);
    void deleteByCartIdAndProductId(Long cartId, Long productId);

}
