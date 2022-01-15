package com.koshelev.spring.web.converters;

import com.koshelev.spring.web.dto.OrderDto;
import com.koshelev.spring.web.entities.Order;
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
        return new OrderDto(order.getId(), order.getUser().getUsername(), order.getTotalPrice(), order.getAddress(), order.getAddress()
                ,order.getOrderItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
    }
}
