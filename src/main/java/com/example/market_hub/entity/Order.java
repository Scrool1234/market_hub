package com.example.market_hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


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

    @Column(name = "discount_amount", nullable = false)
    private BigDecimal discountAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus orderStatus;

    @OneToOne
    @JoinColumn(name = "order_address_id")
    private OrderAddress orderAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
