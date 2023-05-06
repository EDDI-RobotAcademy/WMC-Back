package com.example.Backend.service.product.response;

import com.example.Backend.entity.product.Category;
import com.example.Backend.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ProductListResponse {
    private Long productId;
    private String name;
    private String description;
    private Integer stock;
    private Integer price;
    private Long sold;
    private String firstPhoto;

    private Date regDate;

    private Date updDate;

    private Integer quantity;


//    public ProductListResponse(Product product) {
//
//    public ProductListResponse(Product product, Long sold) {
//            this.productId = product.getProductId();
//            this.name = product.getName();
//            this.description = product.getDescription();
//            this.stock = product.getStock();
//            this.price = product.getPrice();
//            this.sold = sold;
//            this.firstPhoto = product.getImageDataList().get(0).getImageData();
//        }
//    }


    public ProductListResponse(Product product, Long sold) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.sold = sold;
        this.firstPhoto = product.getImageDataList().get(0).getImageData();
        this.regDate = product.getRegDate();
        this.updDate = product.getUpdDate();
    }
}