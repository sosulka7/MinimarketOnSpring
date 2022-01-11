package com.koshelev.spring.web.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price_per_product")
    private Integer pricePerProduct;

    @Column(name = "price")
    private Integer price;

    public OrderItem(Product productId, Order orderId, Integer quantity, Integer pricePerProduct, Integer price) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }
}
