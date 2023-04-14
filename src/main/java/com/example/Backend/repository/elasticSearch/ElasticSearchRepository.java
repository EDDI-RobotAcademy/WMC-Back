//package com.example.Backend.repository.elasticSearch;
//
//import com.example.Backend.entity.product.Product;
//import org.elasticsearch.core.Nullable;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.data.repository.NoRepositoryBean;
//import org.springframework.data.repository.PagingAndSortingRepository;
//
//@NoRepositoryBean
//public interface ElasticSearchRepository<T, ID> extends PagingAndSortingRepository<T, ID>{
//    Page<T> searchSimilar(T entity, @Nullable String[] fields, Pageable pageable);
//
//
//}
