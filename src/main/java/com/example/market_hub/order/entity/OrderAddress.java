package com.example.market_hub.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_addresses")
public class OrderAddress {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house", nullable = false)
    private String house;

    @Column(name = "apartment", nullable = false)
    private String apartment;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
