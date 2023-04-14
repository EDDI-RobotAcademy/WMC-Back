package com.example.Backend.entity.order;

import com.example.Backend.entity.member.Member;
import com.example.Backend.entity.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member buyer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order(Member buyer, Date orderDate) {
        this.buyer = buyer;
        this.orderDate = orderDate;
    }
}
