//package com.example.Backend.service.event;
//
//import com.example.Backend.entity.product.Product;
////import com.example.Backend.repository.elasticSearch.ProductSearchRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class ProductEventListener {
//    private final ProductSearchRepository productSearchRepository;
//
//    @EventListener
//    public void onProductSaved(ProductSavedEvent event) {
//        Product product = event.getProduct();
//        productSearchRepository.save(product);
//    }
//}
