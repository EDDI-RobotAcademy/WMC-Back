package com.example.Backend.service.product;

import com.example.Backend.entity.order.OrderItem;
import com.example.Backend.entity.product.Category;
import com.example.Backend.entity.product.ImageData;
import com.example.Backend.entity.product.Product;
//import com.example.Backend.repository.elasticSearch.ElasticSearchRepository;
//import com.example.Backend.repository.elasticSearch.ProductSearchRepository;
import com.example.Backend.repository.jpa.category.CategoryRepository;
import com.example.Backend.repository.jpa.order.OrderItemRepository;
import com.example.Backend.repository.jpa.product.ImageDataRepository;
import com.example.Backend.repository.jpa.product.ProductRepository;
import com.example.Backend.service.category.CategoryService;
import com.example.Backend.service.event.ProductSavedEvent;
import com.example.Backend.service.product.request.ProductRegisterRequest;
import com.example.Backend.service.product.response.ProductListResponse;
import com.example.Backend.service.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;

    final private ImageDataRepository imageDataRepository;

//    final private CategoryService categoryService;

    final private CategoryRepository categoryRepository;

//    final private ElasticsearchRestTemplate elasticsearchRestTemplate;

//    final private ProductSearchRepository productSearchRepository;

//    private final ApplicationEventPublisher eventPublisher;

    final private OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Boolean register(ProductRegisterRequest productRegisterRequest) {
        log.info("Received ProductRegisterRequest: " + productRegisterRequest);

        Category category = categoryRepository.findById(productRegisterRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        final Product product = productRegisterRequest.toProduct(category);
        productRepository.save(product);
//        productSearchRepository.save(product);
//        eventPublisher.publishEvent(new ProductSavedEvent(product));
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(Long productId) {
        Optional<Product> maybeProduct = productRepository.findById(productId);
        if (maybeProduct.isPresent()) {
            Product product = maybeProduct.get();
            imageDataRepository.deleteAll(product.getImageDataList());
            productRepository.delete(product);
//            productSearchRepository.delete(product);
            return true;
        }
        return false;
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Optional<Product> maybeProduct = productRepository.findById(productId);
        if (maybeProduct.isPresent()) {
            Product product = maybeProduct.get();
            List<ImageData> imageDataList = imageDataRepository.findAllImagesByProductId(productId);
            return new ProductResponse(
                    product.getProductId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock(),
                    imageDataList
            );
        } else {
            return null;
        }

    }

    @Override
    public List<ProductListResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductListResponse> productListResponses = new ArrayList<>();

        for (Product product : products) {
            String firstPhoto = null;
            List<ImageData> images = imageDataRepository.findAllImagesByProductId(product.getProductId());
            if (!images.isEmpty()) {
                firstPhoto = images.get(0).getImageData();
            }

            Integer totalQuantity = orderItemRepository.findTotalQuantityByProduct(product);

            ProductListResponse response = new ProductListResponse(
                    product.getProductId(),
                    product.getName(),
                    product.getDescription(),
                    product.getStock(),
                    product.getPrice(),
                    null,
                    firstPhoto,
                    product.getRegDate(),
                    product.getUpdDate(),
                    totalQuantity

            );
            productListResponses.add(response);
        }

        return productListResponses;
    }

    @Override
    @Transactional
    public List<ProductListResponse> getProductsByCategory(Long categoryId) {
            List<Product> products = productRepository.findByCategoryCategoryId(categoryId);
            List<ProductListResponse> productListResponses = new ArrayList<>();
            for (Product product : products) {
                String firstPhoto = null;
                List<ImageData> images = imageDataRepository.findAllImagesByProductId(product.getProductId());
                if (!images.isEmpty()) {
                    firstPhoto = images.get(0).getImageData();
                }

                Integer totalQuantity = orderItemRepository.findTotalQuantityByProduct(product);

                ProductListResponse response = new ProductListResponse(
                        product.getProductId(),
                        product.getName(),
                        product.getDescription(),
                        product.getStock(),
                        product.getPrice(),
                        null,
                        firstPhoto,
                        product.getRegDate(),
                        product.getUpdDate(),
                        totalQuantity

                );
                productListResponses.add(response);
            }

        return productListResponses;
    }


    @Transactional
    public void decreaseProductStock(Long productId, Integer quantity) {
        Optional<Product> maybeProduct = productRepository.findById(productId);
        if (maybeProduct.isPresent()) {
            Product product = maybeProduct.get();
            product.setStock(product.getStock() - quantity);
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product 없음");
        }
    }

//    @Transactional
//    @Override
//    public List<Product> getAll(String name) {
//        QueryBuilder query = org.elasticsearch.index.query.QueryBuilders.boolQuery()
//                .should(
//                        QueryBuilders.queryStringQuery(name)
//                                .lenient(true)
//                                .field("name")
//                                .field("productId")
//                                .field("price")
//                                .field("stock")
//                ).should(org.elasticsearch.index.query.QueryBuilders.queryStringQuery("*" + name + "*")
//                        .lenient(true)
//                        .field("name")
//                        .field("productId")
//                        .field("price")
//                        .field("stock"));
//
//        NativeSearchQuery build = new NativeSearchQueryBuilder()
//                .withQuery(query)
//                .build();
//
//        SearchHits<Product> searchHits = elasticsearchRestTemplate.search(build, Product.class);
//        List<Product> products = searchHits.getSearchHits().stream()
//                .map(hit -> hit.getContent())
//                .collect(Collectors.toList());
//        return products;
//    }

    @Override
    @Transactional
    public List<ProductListResponse> getMostSoldProductList(Pageable pageable) {
        pageable = PageRequest.of(0, 10);
        List<Object[]> results = productRepository.findTop10ByOrderBySoldDesc(pageable);
        List<Product> products = new ArrayList<>();
        for (Object[] result : results) {
            Product product = (Product) result[0];
            products.add(product);
        }
        List<ProductListResponse> productListResponses = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            String firstPhoto = null;
            List<ImageData> images = imageDataRepository.findAllImagesByProductId(product.getProductId());
            if (!images.isEmpty()) {
                firstPhoto = images.get(0).getImageData();
            }

            Long sold = (Long) results.get(i)[1];

            ProductListResponse response = new ProductListResponse(
                    product,
                    sold
            );
            productListResponses.add(response);
        }

        return productListResponses;
    }

    @Override
    public List<ProductListResponse> search(String keyword){
        List<Product> products = productRepository.findSearchProduct(keyword);
        List<ProductListResponse> productListResponses = new ArrayList<>();
        for (Product product : products) {
            String firstPhoto = null;
            List<ImageData> images = imageDataRepository.findAllImagesByProductId(product.getProductId());
            if (!images.isEmpty()) {
                firstPhoto = images.get(0).getImageData();
            }

            ProductListResponse response = new ProductListResponse(
                    product.getProductId(),
                    product.getName(),
                    product.getDescription(),
                    product.getStock(),
                    product.getPrice(),
                    null,
                    firstPhoto
            );
            productListResponses.add(response);
        }

        return productListResponses;
    }


}
