package com.example.market_hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "warehouses", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {
                        "latitude",
                        "longitude"
                }
        )
})
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private Double lat;

    @Column(name = "longitude")
    private Double lon;

}
