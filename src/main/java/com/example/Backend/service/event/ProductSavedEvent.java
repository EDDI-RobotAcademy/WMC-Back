package com.example.Backend.service.event;

import com.example.Backend.entity.product.Product;
import org.springframework.context.ApplicationEvent;

public class ProductSavedEvent extends ApplicationEvent {
    public ProductSavedEvent(Product source) {
        super(source);
    }

    public Product getProduct() {
        return (Product) getSource();
    }
}

