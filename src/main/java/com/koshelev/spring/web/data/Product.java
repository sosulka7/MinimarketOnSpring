package com.koshelev.spring.web.data;

public class Product {
    private Long id;
    private String title;
    private double cost;

    public Product(Long id, String title, double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public String productInfo(){
        return String.format("ID: %s, Название: %s, Стоимость: %.2f", id, title, cost);
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }
}
