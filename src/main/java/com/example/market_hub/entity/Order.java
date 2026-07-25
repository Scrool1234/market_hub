package com.example.market_hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "subtotal_amount", nullable = false)
    private BigDecimal subtotalAmount;

    @Column(name = "discount_amount", nullable = false)
    private BigDecimal discountAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "order_address_id")
    private OrderAddress orderAddress;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "order"
    )
    List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
