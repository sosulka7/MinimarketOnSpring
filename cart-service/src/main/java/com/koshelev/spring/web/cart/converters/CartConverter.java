package com.koshelev.spring.web.cart.converters;

import com.koshelev.spring.web.api.cart.CartDto;
import com.koshelev.spring.web.api.cart.CartItemDto;
import com.koshelev.spring.web.cart.models.Cart;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartConverter {

    public CartDto modelToDto(Cart cart){
        List<CartItemDto> cartItemDtoList = cart.getItems().stream().map(item ->
                new CartItemDto(item.getProductId(), item.getProductTitle(), item.getQuantity(), item.getPricePerProduct(), item.getPrice())).collect(Collectors.toList());
        return new CartDto(cartItemDtoList, cart.getTotalPrice());
    }
}
