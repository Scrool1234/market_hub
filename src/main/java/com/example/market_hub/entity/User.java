package com.example.market_hub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;


}
