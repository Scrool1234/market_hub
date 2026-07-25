package com.example.market_hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "carts")
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency", nullable = false)
    public String currency;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "cart"
    )
    private List<CartItem> cartItems;

}
