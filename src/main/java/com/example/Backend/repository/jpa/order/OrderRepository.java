package com.example.Backend.repository.jpa.order;

import com.example.Backend.entity.order.Order;
import com.example.Backend.service.order.response.ManagerOrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT new com.example.Backend.service.order.response.ManagerOrderResponse(" +
            "o.id, p.name, oi.price, oi.quantity, o.orderDate, m.username, a, mp.phoneNumber) " +
            "FROM Order o " +
            "JOIN o.orderItems oi " +
            "JOIN oi.product p " +
            "JOIN o.buyer m " +
            "JOIN m.memberProfile mp " +
            "JOIN mp.address a")
    List<ManagerOrderResponse> findAllOrderDetails();

}
