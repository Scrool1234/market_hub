package com.example.market_hub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@Table(name = "inventory_items")
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "reserved_quantity", nullable = false)
    private Long reservedQuantity;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_variant")
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

}
