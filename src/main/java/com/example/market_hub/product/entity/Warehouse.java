package com.example.market_hub.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "warehouses")
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lat", nullable = false)
    private Long latitude;

    @Column(name = "lon", nullable = false)
    private Long lon;
}
