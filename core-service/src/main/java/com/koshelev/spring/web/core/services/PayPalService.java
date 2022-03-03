package com.koshelev.spring.web.core.services;

import com.koshelev.spring.web.api.exceptions.ResourceNotFoundException;
import com.paypal.orders.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayPalService {
    private final OrderService orderService;

    public OrderRequest createOrderRequest(Long orderId){
        com.koshelev.spring.web.core.entities.Order order = orderService.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Заказ с указанным ID не найден."));
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext()
                .brandName("My First Web Market")
                .landingPage("BILLING")
                .shippingPreference("SET_PROVIDED_ADDRESS");
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .referenceId(orderId.toString())
                .description("My First Web Market Order")
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("RUB").value(String.valueOf(order.getTotalPrice()))
                        .amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("RUB").value(String.valueOf(order.getTotalPrice())))))
                .items(order.getOrderItems().stream()
                        .map(item -> new Item()
                                .name(item.getProduct().getTitle())
                                .unitAmount(new Money().currencyCode("RUB").value(String.valueOf(item.getPrice())))
                                .quantity(String.valueOf(item.getQuantity())))
                        .collect(Collectors.toList()))
                .shippingDetail(new ShippingDetail().name(new Name().fullName(order.getUsername()))
                        .addressPortable(new AddressPortable().addressLine1(order.getAddress()).adminArea2("Moscow").countryCode("RU")));
        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);
        return orderRequest;
    }
}
