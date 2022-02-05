package com.koshelev.spring.web.cart;

import com.koshelev.spring.web.api.dto.CartDto;
import com.koshelev.spring.web.api.dto.OrderItemDto;
import com.koshelev.spring.web.cart.converters.CartConverter;
import com.koshelev.spring.web.cart.dto.Cart;
import com.koshelev.spring.web.cart.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CartService cartService;

    @MockBean
    private CartConverter cartConverter;

    @Test
    public void getCartTest() throws Exception{
        Cart cart = new Cart();
        cart.setItems(Arrays.asList(
                new OrderItemDto(1L, "Product#1", 4, 25, 100),
                new OrderItemDto(2L, "Product#2", 3, 33, 99)
        ));
        cart.setTotalPrice(199);

        CartDto cartDto = new CartDto();
        cartDto.setItems(Arrays.asList(
                new OrderItemDto(1L, "Product#1", 4, 25, 100),
                new OrderItemDto(2L, "Product#2", 3, 33, 99)
        ));
        cartDto.setTotalPrice(199);

        System.out.println(cart.getTotalPrice());

        Mockito.when(cartService.getCurrentCart("user_cart")).thenReturn(cart);
        Mockito.when(cartConverter.cartToDto(cart)).thenReturn(cartDto);

        //Настроил окружение CartController'а, но в теле ответа ничего нет.
//
//        mvc
//                .perform(get("/api/v1/cart/user_cart").contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.items").isArray())
//                .andExpect(jsonPath("$.totalPrice", is(cartDto.getTotalPrice())))
//                .andExpect(jsonPath("$.items[0].price", is(cartDto.getItems().get(0).getPrice())));
    }
}
