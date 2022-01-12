package com.koshelev.spring.web.dto;

import com.koshelev.spring.web.entities.Product;
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

    public void addProduct(Product product){
        if (addProduct(product.getId())){
            return;
        }
        items.add(new OrderItemDto(product));
        recalculated();
    }

    public boolean addProduct(Long id){
        for (OrderItemDto o : items) {
            if (o.getProductId().equals(id)){
                o.changeQuantity(1);
                recalculated();
                return true;
            }
        }
        return false;
    }

    public void decreaseProduct(Long id){
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()){
            OrderItemDto o = iter.next();
            if (o.getProductId().equals(id)){
                o.changeQuantity(-1);
                if (o.getQuantity() <= 0){
                    iter.remove();
                }
                recalculated();
                return;
            }
        }
    }
    
    public void removeProduct(Long id){
        items.removeIf(o -> o.getProductId().equals(id));
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

}
