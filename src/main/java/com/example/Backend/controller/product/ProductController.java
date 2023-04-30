package com.example.Backend.controller.product;

import com.example.Backend.controller.product.form.ProductRegisterForm;
import com.example.Backend.entity.product.Category;
import com.example.Backend.entity.product.Product;
import com.example.Backend.service.category.CategoryService;
import com.example.Backend.service.product.ProductService;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import com.example.Backend.service.product.response.ProductListResponse;
import com.example.Backend.service.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class ProductController {

    final private ProductService productService;

    final private CategoryService categoryService;

    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean productRegister(@RequestBody ProductRegisterForm form) throws IOException {
        log.info("productRegister(): " + form);
//        log.info("Files received: " + form.getFileList().size());

        List<String> savedFiles = form.getFileNames();

        ProductRegisterRequest request = new ProductRegisterRequest(form.getName(), form.getDescription(), form.getStock(), form.getPrice(), form.getCategoryId(), savedFiles);

//        Product product = request.toProduct(category);
        return productService.register(request);
    }

//    private List<String> saveFiles(List<MultipartFile> fileList) {
//        List<String> savedFilePaths = new ArrayList<>();
//        String basePath = "/home/ec2-user/project/frontend/html/img/";
//
//        for (MultipartFile multipartFile : fileList) {
//            log.info("saveFiles() - filename: " + multipartFile.getOriginalFilename());
//            log.info("saveFiles() - file size: " + multipartFile.getSize());
//
//            String savedFileName = basePath + multipartFile.getOriginalFilename();
//            savedFilePaths.add("home/ec2-user/project/frontend/html/img/" + multipartFile.getOriginalFilename());
//
//            try {
//                FileOutputStream writer = new FileOutputStream(savedFileName);
//                writer.write(multipartFile.getBytes());
//                writer.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return savedFilePaths;
//    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestBody Long productId) {

        return productService.delete(productId);
    }

    @GetMapping("/list")
    public List<ProductListResponse> getAllProduct() {

        return productService.getAllProducts();
    }

    @GetMapping("/search")
    public List<ProductListResponse> productsList(@RequestParam(value = "keyword", required = false) String keyword) {
        log.info("키워드 = " + keyword);

        if (keyword == null || keyword.length() == 0){
            return productService.getAllProducts();
        }else {
//            searchService.registerOrAddCntKeyWord(keyword);
            return productService.search(keyword);
        }
    }

    @GetMapping("/detail")
    public ProductResponse getProductDetail(@RequestParam Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/listByCategory")
    public List<ProductListResponse> getProductsByCategory(@RequestParam Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

//    @GetMapping("/search/{name}")
//    public List<Product> search(@RequestParam("name") String name) {
//        return productService.getAll(name);
//    }

    @GetMapping("/mostsoldlist")
    public List<ProductListResponse> getMostSoldProductList() {

        Pageable pageable = PageRequest.of(0, 10);
        return productService.getMostSoldProductList(pageable);
    }
}
