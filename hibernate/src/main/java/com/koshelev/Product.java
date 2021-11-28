package com.koshelev;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="cost")
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

    public Product(){

    }
    public Product(String title, Double cost){
        this.title = title;
        this.cost = cost;
    }
}
