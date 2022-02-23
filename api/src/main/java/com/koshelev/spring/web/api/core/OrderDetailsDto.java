package com.koshelev.spring.web.api.core;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель деталей заказа. Заполняются пользователем и отправляются на серверную часть для оформления заказа.")
public class OrderDetailsDto {
    private String phoneNumber;
    private String address;

    public OrderDetailsDto(String phoneNumber, String address) {
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public OrderDetailsDto(){

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
