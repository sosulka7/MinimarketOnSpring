package com.koshelev.spring.web.api.cart;

import com.koshelev.spring.web.api.core.OrderItemDto;

import java.util.List;

public class CartDto {
    private List<CartItemDto> items;
    private int totalPrice;

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartDto(List<CartItemDto> items, int totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }
    public CartDto(){

    }
}
