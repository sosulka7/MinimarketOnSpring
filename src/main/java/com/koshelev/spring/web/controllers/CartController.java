package com.koshelev.spring.web.controllers;

import com.koshelev.spring.web.dto.Cart;
import com.koshelev.spring.web.dto.StringResponse;
import com.koshelev.spring.web.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{uuid}")
    public Cart getCart (Principal principal, @PathVariable String uuid){
        String username = null;
        if (principal != null){
            username = principal.getName();
        }
        return cartService.getCurrentCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/generate")
    public StringResponse getCart(){
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(Principal principal, @PathVariable String uuid, @PathVariable Long productId){
        String username = null;
        if (principal != null){
            username = principal.getName();
        }
        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart(Principal principal, @PathVariable String uuid){
        String username = null;
        if (principal != null){
            username = principal.getName();
        }
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrement(Principal principal, @PathVariable String uuid, @PathVariable Long productId){
        String username = null;
        if (principal != null){
            username = principal.getName();
        }
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    public void remove(Principal principal, @PathVariable String uuid, @PathVariable Long productId){
        String username = null;
        if (principal != null){
            username = principal.getName();
        }
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }
    @GetMapping("/{uuid}/merge")
    public void merge(Principal principal, @PathVariable String uuid){
        String username = null;
        if (principal != null){
            username = principal.getName();
        }
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
