package com.koshelev.spring.web.api.core;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;


@Schema(description = "Основная модель заказа")
public class OrderDto {

    @Schema(description = "ID заказа", required = true, example = "3")
    private Long id;

    @Schema(description = "Имя пользователя", required = true, example = "bob")
    private String username;

    @Schema(description = "Суммарная стоимость заказа", required = true, example = "300.00")
    private BigDecimal totalPrice;

    @Schema(description = "Адрес, на который сделан заказ", required = true, example = "РФ, Питер, ул. Невский проспект, д. 13, кв. 2")
    private String address;

    @Schema(description = "Телефонный номер", required = true, example = "88005553535")
    private String phone;

    @Schema(description = "Позиции заказа", required = true)
    private List<OrderItemDto> orderItems;

    @Schema(description = "Статус заказа", required = true, example = "CREATED")
    private String orderStatus;

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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

    public OrderDto(Long id, String username, BigDecimal totalPrice, String address, String phone, String orderStatus, List<OrderItemDto> orderItems) {
        this.id = id;
        this.username = username;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phone = phone;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
    }
}
