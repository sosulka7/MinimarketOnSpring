package com.koshelev.spring.web.cart.dto;

import com.koshelev.spring.web.api.dto.OrderItemDto;
import com.koshelev.spring.web.api.dto.ProductDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<OrderItemDto> items;
    private int totalPrice;

    public Cart(){
        this.items = new ArrayList<>();
    }

    public void add(ProductDto productDto){
        if (add(productDto.getId())){
            return;
        }
        items.add(new OrderItemDto(productDto));
        recalculated();
    }

    public boolean add(Long id){
        for (OrderItemDto o : items) {
            if (o.getProductId().equals(id)){
                o.changeQuantity(1);
                recalculated();
                return true;
            }
        }
        return false;
    }

    public void decrement(Long productId){
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()){
            OrderItemDto o = iter.next();
            if (o.getProductId().equals(productId)){
                o.changeQuantity(-1);
                if (o.getQuantity() <= 0){
                    iter.remove();
                }
                recalculated();
                return;
            }
        }
    }
    
    public void remove(Long productId){
        items.removeIf(o -> o.getProductId().equals(productId));
        recalculated();
    }

    public void clear(){
        items.clear();
        totalPrice = 0;
    }

    private void recalculated(){
        totalPrice = 0;
        for (OrderItemDto o : items) {
            totalPrice += o.getPrice();
        }
    }

    public void merge(Cart another){
        for (OrderItemDto anotherItem : another.items) {
            boolean merged = false;
            for (OrderItemDto myItem : items) {
                if (myItem.getProductId().equals(anotherItem.getProductId())){
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged){
                items.add(anotherItem);
            }
        }
        recalculated();
        another.clear();
    }
}
