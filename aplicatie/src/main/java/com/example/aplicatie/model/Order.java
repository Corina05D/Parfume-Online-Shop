package com.example.aplicatie.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany
    @JoinTable(name="order_products",
               joinColumns =@JoinColumn(name="order_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products=new ArrayList<>();

    public Order(User user,ArrayList<Product> products) {
        this.user=user;
        this.products=products;
    }
    public Order(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }
}
