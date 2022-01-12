package com.koshelev.spring.web.dto;

import com.koshelev.spring.web.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto (Product product){
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.quantity = 1;
        this.pricePerProduct = product.getCost();
        this.price = product.getCost();
    }

    public void changeQuantity(int delta){
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }
}
