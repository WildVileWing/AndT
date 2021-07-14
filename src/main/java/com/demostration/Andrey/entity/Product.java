package com.demostration.Andrey.entity;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false, length = 400)
    private String description;

    @Column(nullable = false, length = 15, precision = 2)
    private Double price;

    @ManyToOne
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
