package com.example.Backend.repository.jpa.order;

import com.example.Backend.entity.order.OrderItem;
import com.example.Backend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByProduct(Product product);

    @Query("SELECT SUM(oi.quantity) FROM OrderItem oi WHERE oi.product = :product")
    Integer findTotalQuantityByProduct(@Param("product") Product product);

}

