package com.example.Backend.controller.product.form;


import com.example.Backend.entity.product.ImageData;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductRegisterForm {

    private String name;
    private String description;
    private Integer stock;
    private Integer price;
    private List<ImageData> imageDataList;

    public ProductRegisterRequest toProductRegisterRequest() {
        return new ProductRegisterRequest(name, description, stock, price, imageDataList);
    }
}
