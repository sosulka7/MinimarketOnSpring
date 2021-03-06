package com.koshelev.spring.web.cart.services;

import com.koshelev.spring.web.api.core.ProductDto;
import com.koshelev.spring.web.api.exceptions.AppError;
import com.koshelev.spring.web.api.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.cart.integrations.ProductServiceIntegration;
import com.koshelev.spring.web.cart.integrations.RecServiceIntegration;
import com.koshelev.spring.web.cart.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ProductServiceIntegration productServiceIntegration;
    private final RecServiceIntegration recServiceIntegration;

    @Value("${utils.cart.prefix}")
    private String cartPrefix;


    public String getCartUuidFromSuffix(String suffix){
        return cartPrefix + suffix;
    }

    public String generateCartUuid(){
        return UUID.randomUUID().toString();
    }

    public Cart getCurrentCart(String cartKey){
        if (!redisTemplate.hasKey(cartKey)){
            redisTemplate.opsForValue().set(cartKey, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);
    }

    public void addToCart(String cartKey, Long id){
        ProductDto productDto = productServiceIntegration.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину. Продукт не найден. ID: " + id));
        recServiceIntegration.addProductToRecService(productDto.getId());
        execute(cartKey, c -> c.add(productDto));
    }

    public void clearCart(String cartKey){
        execute(cartKey, Cart::clear);
    }

    public void removeItemFromCart(String cartKey, Long productId){
        execute(cartKey, c -> c.remove(productId));
    }

    public void decrementItem(String cartKey, Long productId){
        execute(cartKey, c -> c.decrement(productId));
    }

    public void merge(String userCartKey, String guestCartKey){
        Cart guestCart = getCurrentCart(guestCartKey);
        Cart userCart = getCurrentCart(userCartKey);
        userCart.merge(guestCart);
        updateCart(guestCartKey, guestCart);
        updateCart(userCartKey, userCart);
    }

    private void execute (String cartKey, Consumer<Cart> action){
        Cart cart = getCurrentCart(cartKey);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    public void updateCart(String cartKey, Cart cart){
        redisTemplate.opsForValue().set(cartKey, cart);
    }

}
