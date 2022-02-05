package com.koshelev.spring.web.cart;

import com.koshelev.spring.web.api.dto.ProductDto;
import com.koshelev.spring.web.cart.services.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @BeforeEach
    public void initCarts(){
        cartService.clearCart("guest_cart");
        cartService.clearCart("user_cart");
    }

    @Test
    public void addToCartTest(){
        ProductDto productDto = new ProductDto(1L, "Product-#1", 100);
        ProductDto productDto1 = new ProductDto(2L, "Product-#2", 200);
        cartService.addToCart("user_cart", productDto);
        cartService.addToCart("user_cart", productDto);
        cartService.addToCart("user_cart", productDto1);
        Assertions.assertEquals(2, cartService.getCurrentCart("user_cart").getItems().size());
        Assertions.assertEquals(2, cartService.getCurrentCart("user_cart").getItems().get(0).getQuantity());
    }

    @Test
    public void decrementTest(){
        ProductDto productDto = new ProductDto(1L, "Product-#1", 100);
        cartService.addToCart("user_cart", productDto);
        cartService.addToCart("user_cart", productDto);
        cartService.decrementItem("user_cart", 1L);
        cartService.decrementItem("user_cart", 2L);
        Assertions.assertEquals(1, cartService.getCurrentCart("user_cart").getItems().get(0).getQuantity());
        cartService.decrementItem("user_cart", 1L);
        Assertions.assertEquals(0, cartService.getCurrentCart("user_cart").getItems().size());
    }

    @Test
    public void recalculateTest(){
        ProductDto productDto = new ProductDto(1L, "Product-#1", 100);
        ProductDto productDto1 = new ProductDto(2L, "Product-#2", 200);
        cartService.addToCart("user_cart", productDto);
        cartService.addToCart("user_cart", productDto1);
        Assertions.assertEquals(300, cartService.getCurrentCart("user_cart").getTotalPrice());
        cartService.decrementItem("user_cart", productDto.getId());
        Assertions.assertEquals(200, cartService.getCurrentCart("user_cart").getTotalPrice());
    }

    @Test
    public void mergeTest(){
        ProductDto productDto = new ProductDto(1L, "Product-#1", 100);
        ProductDto productDto1 = new ProductDto(2L, "Product-#2", 200);
        cartService.addToCart("guest_cart", productDto);
        cartService.addToCart("guest_cart", productDto1);
        cartService.addToCart("user_cart", productDto1);
        cartService.merge("user_cart", "guest_cart");
        Assertions.assertEquals(2, cartService.getCurrentCart("user_cart").getItems().size());
        Assertions.assertEquals(500, cartService.getCurrentCart("user_cart").getTotalPrice());
        Assertions.assertEquals(0, cartService.getCurrentCart("guest_cart").getItems().size());
        Assertions.assertEquals(0, cartService.getCurrentCart("guest_cart").getTotalPrice());
    }

}
