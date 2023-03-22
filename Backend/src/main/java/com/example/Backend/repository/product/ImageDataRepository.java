package com.example.Backend.repository.product;

import com.example.Backend.entity.product.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
    @Query("select i from ImageData i join i.product p where p.productId = :productId")
    List<ImageData> findAllImagesByProductId(Long productId);

}