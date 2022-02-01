package com.koshelev.spring.web.core.converters;

import com.koshelev.spring.web.core.dto.OrderDto;
import com.koshelev.spring.web.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final OrderItemConverter orderItemConverter;

    public Order dtoToEntity(OrderDto orderDto){
        return new Order();
    }

    public OrderDto entityToDto(Order order){
        return new OrderDto(order.getId(), order.getUsername(), order.getTotalPrice(), order.getAddress(), order.getAddress()
                ,order.getOrderItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
    }
}
