package com.example.Backend.entity.product;
import com.example.Backend.entity.order.Order;
import com.example.Backend.entity.order.OrderItem;

import com.example.Backend.entity.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import org.springframework.data.elasticsearch.annotations.FieldType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
//@Document(indexName = "product")
@JsonIgnoreProperties("orderItemList")
public class Product {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Field(type = FieldType.Keyword)
    private Long productId;

    @Column(length = 128, nullable = false)
//    @Field(type = FieldType.Text)
    private String name;

    @Column(length = 128, nullable = false)
    private String description;

    @Column
    private Integer stock;

    @Column
    private Integer price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
//    @Field(type = FieldType.Auto , includeInParent = true)
    private Category category;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ImageData> imageDataList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date updDate;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
    public Product(String name, String description, Integer stock, Integer price, Category category) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.category = category;
    }

    public void addImageData(ImageData imageData) {
        imageData.setProduct(this);
        imageDataList.add(imageData);
    }
    public Product(String name, String description, Integer stock, Integer price, Category category, List<ImageData> imageDataList) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.category = category;
        this.imageDataList = imageDataList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }

}

