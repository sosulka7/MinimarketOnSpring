package com.koshelev.spring.web.api.cart;

import com.koshelev.spring.web.api.core.ProductDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Модель пункта корзины")
public class CartItemDto {

    @Schema(description = "ID товара", required = true, example = "1")
    private Long productId;

    @Schema(description = "Название товара", required = true, example = "Milk")
    private String productTitle;

    @Schema(description = "Количество товара в пункте корзины", required = true, example = "3")
    private int quantity;

    @Schema(description = "Стоимость одной единицы товара", required = true, example = "55.00")
    private BigDecimal pricePerProduct;

    @Schema(description = "Полная стоимость пункта корзины", required = true, example = "165.00")
    private BigDecimal price;

    public CartItemDto(){

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CartItemDto(Long productId, String productTitle, int quantity, BigDecimal pricePerProduct, BigDecimal price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }
}
