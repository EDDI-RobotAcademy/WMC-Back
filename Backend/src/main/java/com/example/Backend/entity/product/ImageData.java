package com.example.Backend.entity.product;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private String imageData;
}
