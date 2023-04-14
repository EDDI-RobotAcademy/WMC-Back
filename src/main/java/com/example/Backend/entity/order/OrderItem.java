package com.example.Backend.entity.order;

import com.example.Backend.entity.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    public OrderItem(Order order, Product product, Integer quantity, Integer price) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}
