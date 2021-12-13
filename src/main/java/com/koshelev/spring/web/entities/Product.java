package com.koshelev.spring.web.entities;

import com.koshelev.spring.web.dto.ProductDto;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private Double cost;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getCost() {
        return cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Product(ProductDto productDto){
        this.id = productDto.getId();
        this.title = productDto.getTitle();
        this.cost = productDto.getCost();
    }
    public Product() {
    }
}
