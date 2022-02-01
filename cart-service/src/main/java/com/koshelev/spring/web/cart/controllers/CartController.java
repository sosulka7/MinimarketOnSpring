package com.koshelev.spring.web.cart.controllers;

import com.koshelev.spring.web.api.dto.CartDto;
import com.koshelev.spring.web.api.dto.ProductDto;
import com.koshelev.spring.web.api.dto.StringResponse;
import com.koshelev.spring.web.cart.converters.CartConverter;
import com.koshelev.spring.web.cart.dto.Cart;
import com.koshelev.spring.web.cart.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final RestTemplate restTemplate;
    private final CartConverter cartConverter;

    @GetMapping("/{uuid}")
    public CartDto getCart (@RequestHeader (required = false) String username, @PathVariable String uuid){
        return cartConverter.cartToDto(cartService.getCurrentCart(getCurrentCartUuid(username, uuid)));
    }

    // ниже 2 метода для заказов, не смог найти, как можно в RestTemplates в хедеры
    // подшить username. Такой способ первый пришел в голову
    @GetMapping
    public CartDto getCartForOrder (@RequestParam String username){
        String cartKey = cartService.getCartUuidFromSuffix(username);
        return cartConverter.cartToDto(cartService.getCurrentCart(cartKey));
    }
    @GetMapping("/clear")
    public void clearCart(@RequestParam String username){
        String cartKey = cartService.getCartUuidFromSuffix(username);
        cartService.clearCart(cartKey);
    }

    @GetMapping("/generate")
    public StringResponse getCart(){
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(@RequestHeader (required = false) String username, @PathVariable String uuid, @PathVariable Long productId){
        ProductDto productDto = restTemplate.getForObject("http://localhost:8189/web-market-core/api/v1/products/" + productId, ProductDto.class);
        cartService.addToCart(getCurrentCartUuid(username, uuid), productDto);
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart(@RequestHeader (required = false) String username, @PathVariable String uuid){
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrement(@RequestHeader (required = false) String username, @PathVariable String uuid, @PathVariable Long productId){
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    public void remove(@RequestHeader (required = false) String username, @PathVariable String uuid, @PathVariable Long productId){
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }
    @GetMapping("/{uuid}/merge")
    public void merge(@RequestHeader String username, @PathVariable String uuid){
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private String getCurrentCartUuid(String username, String uuid){
        if (username != null){
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}
