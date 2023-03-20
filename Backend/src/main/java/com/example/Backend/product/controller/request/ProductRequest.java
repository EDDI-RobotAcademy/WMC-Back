package com.example.Backend.product.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//dto같은 역할
@Getter
@RequiredArgsConstructor
public class ProductRequest {

    final private String productName;
    final private String writer;
    final private String content;
    final private Integer price;

}
