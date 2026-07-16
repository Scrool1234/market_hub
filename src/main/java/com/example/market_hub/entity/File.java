package com.example.market_hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "files")
@NoArgsConstructor
@AllArgsConstructor
public class File {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bucket_name")
    private String bucket;

    @Column(name = "object_name")
    private String object;

    @Column(name = "type")
    private String type;

}
