package com.example.Backend.service.order;

import com.example.Backend.entity.member.Member;
import com.example.Backend.entity.order.Order;
import com.example.Backend.entity.order.OrderItem;
import com.example.Backend.entity.product.Product;
import com.example.Backend.repository.jpa.member.MemberRepository;
import com.example.Backend.repository.jpa.order.OrderItemRepository;
import com.example.Backend.repository.jpa.order.OrderRepository;
import com.example.Backend.repository.jpa.product.ProductRepository;
import com.example.Backend.service.order.request.OrderItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    @Override
    public void createOrderItem(Order order, OrderItemRequest orderItemRequest) {
        Product product = productRepository.findById(orderItemRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        int price = product.getPrice() * orderItemRequest.getQuantity();
        OrderItem orderItem = new OrderItem(order, product, orderItemRequest.getQuantity(), price);
        orderItemRepository.save(orderItem);
    }

    @Transactional
    @Override
    public void createOrder(Long memberId, List<OrderItemRequest> orderItems) {
        Member buyer = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Order order = new Order(buyer, new Date());
        Order savedOrder = orderRepository.save(order);

        for (OrderItemRequest orderItemRequest : orderItems) {
            Product product = productRepository.findById(orderItemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            int totalPrice = product.getPrice() * orderItemRequest.getQuantity();

            OrderItem orderItem = new OrderItem(savedOrder, product, orderItemRequest.getQuantity(), totalPrice);
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public boolean isProductEnough(Long productId, Integer quantity){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return product.getStock() >= quantity;
    }




}
