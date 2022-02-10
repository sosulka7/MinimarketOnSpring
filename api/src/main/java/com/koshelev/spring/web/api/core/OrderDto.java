package com.koshelev.spring.web.api.core;
import java.util.List;


public class OrderDto {
    private Long id;
    private String username;
    private Integer totalPrice;
    private String address;
    private String phone;
    private List<OrderItemDto> orderItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderDto() {
    }

    public OrderDto(Long id, String username, Integer totalPrice, String address, String phone, List<OrderItemDto> orderItems) {
        this.id = id;
        this.username = username;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phone = phone;
        this.orderItems = orderItems;
    }
}
