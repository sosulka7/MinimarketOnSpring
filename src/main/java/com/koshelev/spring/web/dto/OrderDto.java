package com.koshelev.spring.web.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String username;
    private Integer totalPrice;
    private String address;
    private String phone;
    private List<OrderItemDto> orderItems;
}
