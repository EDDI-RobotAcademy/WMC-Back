package com.example.Backend.repository.jpa.order;

import com.example.Backend.entity.member.Member;
import com.example.Backend.entity.order.Order;
import com.example.Backend.service.order.response.ManagerOrderResponse;
import com.example.Backend.service.order.response.MyOrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    void deleteAllByBuyer(Member buyer);

    @Query("SELECT new com.example.Backend.service.order.response.MyOrderResponse(" +
            "o.id, p.productId, p.name, oi.price, oi.quantity, o.orderDate, a, img.imageData) " +
            "FROM Order o " +
            "JOIN o.orderItems oi " +
            "JOIN oi.product p " +
            "JOIN p.imageDataList img " +
            "JOIN o.buyer m " +
            "JOIN m.memberProfile mp " +
            "JOIN mp.address a " +
            "WHERE m.id = :memberId AND img.id = (SELECT MIN(subImg.id) FROM ImageData subImg WHERE subImg.product = p)")
    List<MyOrderResponse> findAllMyOrderDetails(@Param("memberId") Long memberId);


}
