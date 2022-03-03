package com.koshelev.spring.web.core.controllers;

import com.koshelev.spring.web.api.cart.CartDto;
import com.koshelev.spring.web.api.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.core.converters.OrderConverter;

import com.koshelev.spring.web.api.core.OrderDetailsDto;
import com.koshelev.spring.web.api.core.OrderDto;
import com.koshelev.spring.web.core.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Контроллер для заказов", description = "Методы для заказов")
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @Operation(
            summary = "Запрос на создание нового заказа",
            responses = {
                    @ApiResponse(
                            description = "Создание заказа прошло успешно", responseCode = "200"
                    )

            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody @Parameter(description = "Детали заказа: адрес и номер телефона", required = true) OrderDetailsDto orderDetailsDto,
                            @RequestHeader @Parameter(required = true) String username){
        orderService.createOrder(orderDetailsDto, username);
    }

    @Operation(
            summary = "Получение списка заказов пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )

            }
    )
    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader @Parameter(required = true) String username){
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderConverter.entityToDto(orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("ORDER 404")));
    }
}
