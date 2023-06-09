package com.example.Backend.service.product.response;

import com.example.Backend.entity.product.ImageData;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ProductResponse {
    final private Long productId;
    final private String name;
    final private String description;
    final private Integer price;
    final private Integer stock;
    final private List<ImageData> imageDataList;

}
