package com.example.market_hub.cart.entity;

import com.example.market_hub.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Builder
@Table(name = "carts")
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "cart"
    )
    @Builder.Default
    private List<CartItem> cartItems = new ArrayList<>();

}
