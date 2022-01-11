package com.koshelev.spring.web.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "orderId")
    private List<OrderItem> orderItems;


    public Order(User userId, Integer totalPrice, String address, String phone) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phone = phone;
    }
}
