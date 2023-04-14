package com.example.Backend.repository.jpa.order;

import com.example.Backend.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
