package com.koshelev.spring.web.cart.converters;

import com.koshelev.spring.web.api.dto.CartDto;
import com.koshelev.spring.web.cart.dto.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartConverter {

    public CartDto cartToDto(Cart cart){
        return new CartDto(cart.getItems(), cart.getTotalPrice());
    }
}
