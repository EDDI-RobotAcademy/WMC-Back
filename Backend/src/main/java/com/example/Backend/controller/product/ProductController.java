package com.example.Backend.controller.product;

import com.example.Backend.controller.product.form.ProductRegisterForm;
import com.example.Backend.service.product.ProductService;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import com.example.Backend.service.product.response.ProductListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ProductController {

    final private ProductService productService;

    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean productRegister(@ModelAttribute ProductRegisterForm form) throws IOException {
        log.info("productRegister(): " + form);

        List<String> savedFiles;

        if (form.getFileList() == null || form.getFileList().isEmpty()) {
            savedFiles = new ArrayList<>();
            String defaultFilePath = "assets/productImages/default-image.jpg";
            savedFiles.add(defaultFilePath);
        } else {
            savedFiles = saveFiles(form.getFileList());
        }

        ProductRegisterRequest request = new ProductRegisterRequest(form.getName(), form.getDescription(), form.getStock(), form.getPrice(), savedFiles);

        return productService.register(request);
    }

    private List<String> saveFiles(List<MultipartFile> fileList) {
        List<String> savedFilePaths = new ArrayList<>();
        String basePath = "../../../finalProject/WMC-Front/frontend/src/assets/productImages/";

        for (MultipartFile multipartFile : fileList) {
            log.info("saveFiles() - filename: " + multipartFile.getOriginalFilename());
            String savedFileName = basePath + multipartFile.getOriginalFilename();
            savedFilePaths.add(savedFileName);

            try {
                FileOutputStream writer = new FileOutputStream(savedFileName);
                writer.write(multipartFile.getBytes());
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return savedFilePaths;
    }

    @GetMapping("/list")
    public List<ProductListResponse> getAllProduct() {
        return productService.getAllProducts();
    }
}
