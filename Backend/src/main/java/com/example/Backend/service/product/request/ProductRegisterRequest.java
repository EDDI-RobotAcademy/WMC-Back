package com.example.Backend.service.product.request;

import com.example.Backend.entity.product.ImageData;
import com.example.Backend.entity.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class ProductRegisterRequest {

    final private String name;
    final private String description;
    final private Integer stock;
    final private Integer price;
    final private List<ImageData> imageDataList;

    public ProductRegisterRequest toProductRegisterRequest() {
        return new ProductRegisterRequest(name, description, stock, price, imageDataList);
    }

    public Product toProduct() {
        return new Product(name, description, stock, price, imageDataList);
    }
}
