package com.koshelev.spring.web.core.dto;
import com.koshelev.spring.web.api.dto.OrderItemDto;
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
