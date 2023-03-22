package com.example.Backend.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(length = 128, nullable = false)
    private String name;

    @Column(length = 128, nullable = false)
    private String description;

    @Column
    private Integer stock;

    @Column
    private Integer price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ImageData> imageDataList = new ArrayList<>();

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date updDate;

    public Product(String name, String description, Integer stock, Integer price) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
    }

    public void addImageData(ImageData imageData) {
        imageData.setProduct(this);
        imageDataList.add(imageData);
    }
    public Product(String name, String description, Integer stock, Integer price, List<ImageData> imageDataList) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.imageDataList = imageDataList;
    }

}

