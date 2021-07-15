package com.demostration.Andrey.entity;
import javax.persistence.*;

@Entity
public class OrderProduct {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer countProduct;

    @ManyToOne
    private ClientOrder clientOrder;

    @ManyToOne
    private Product product;

    public ClientOrder getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(Integer countProduct) {
        this.countProduct = countProduct;
    }
}
