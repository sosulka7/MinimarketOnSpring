package com.koshelev.spring.web.cart.controllers;

import com.koshelev.spring.web.api.cart.CartDto;
import com.koshelev.spring.web.api.dto.StringResponse;
import com.koshelev.spring.web.cart.converters.CartConverter;
import com.koshelev.spring.web.cart.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Tag(name = "Контроллер для корзины", description = "Методы для работы с корзиной")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @Operation(
            summary = "Запрос на получение корзины пользователя. По UUID возвращает корзину гостя, по username в header возвращает корзину авторизированного пользователя.",
            responses = {
                    @ApiResponse(
                            description = "Вернет корзину", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CartDto.class)
                            )
                    )
            }
    )
    @GetMapping("/{uuid}")
    public CartDto getCart (@RequestHeader (required = false) String username,
                            @PathVariable @Parameter(description = "UUID корзины гостя") String uuid){
        return cartConverter.modelToDto(cartService.getCurrentCart(getCurrentCartUuid(username, uuid)));
    }

    @Operation(
            summary = "Метод для генерации UUID не вошедшего пользователя.",
            responses = {
                    @ApiResponse(
                            description = "Сгенерирует UUID и подошьет его в storage браузера", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class)
                            )
                    )
            }
    )
    @GetMapping("/generate")
    public StringResponse getCart(){
        return new StringResponse(cartService.generateCartUuid());
    }

    @Operation(
            summary = "Запрос на добавление товара в корзину по его ID",
            responses = {
                    @ApiResponse(
                            description = "Указанный товар добавится в корзину", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CartDto.class)
                            )
                    )
            }
    )
    @GetMapping("/{uuid}/add/{productId}")
    public void add(@RequestHeader (required = false) String username,
                    @PathVariable @Parameter(description = "UUID корзины гостя") String uuid,
                    @PathVariable @Parameter(description = "ID добавляемого товара", required = true) Long productId){
        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }

    @Operation(
            summary = "Запрос на отчистку корзины по UUID или username в headers",
            responses = {
                    @ApiResponse(
                            description = "Текущая корзина отчистится от товаров", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/clear")
    public void clearCart(@RequestHeader (required = false) String username,
                          @PathVariable @Parameter(description = "UUID корзины гостя", required = false) String uuid){
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @Operation(
            summary = "Запрос на уменьшение количества товара в корзине на 1 единицу по его ID",
            responses = {
                    @ApiResponse(
                            description = "Количество указанного товара уменьшится на 1 единицу", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrement(@RequestHeader (required = false) String username,
                          @PathVariable @Parameter(description = "UUID корзины гостя") String uuid,
                          @PathVariable @Parameter(description = "ID товара, находящегося в корзине", required = true) Long productId){
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @Operation(
            summary = "Запрос удаление товара по ID",
            responses = {
                    @ApiResponse(
                            description = "Указанный товар полностью удалится из корзины", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/remove/{productId}")
    public void remove(@RequestHeader (required = false) String username,
                       @PathVariable @Parameter(description = "UUID корзины гостя") String uuid,
                       @PathVariable @Parameter(description = "ID товара, находящегося в корзине", required = true) Long productId){
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    @Operation(
            summary = "Объединение корзины гостя и корзины пользователя",
            responses = {
                    @ApiResponse(
                            description = "Товары в корзинах успешно объединятся, товары из корзины гостя удалятся", responseCode = "200"
                    )
            }
    )
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
