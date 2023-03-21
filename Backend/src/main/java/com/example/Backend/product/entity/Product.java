package com.example.Backend.product.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(length = 128, nullable = false)
    private String productName;

    @Lob
    private String content;

    private Integer price;

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date updDate;

}
