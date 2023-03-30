package com.example.Backend.controller.product.form;

import com.example.Backend.service.product.request.ProductRegisterRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

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
    private List<MultipartFile> fileList;

    public ProductRegisterRequest toProductRegisterRequest(List<String> savedFilePaths) {
        return new ProductRegisterRequest(name, description, stock, price, savedFilePaths);
    }
}
