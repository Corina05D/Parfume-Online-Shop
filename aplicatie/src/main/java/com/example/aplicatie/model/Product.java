package com.example.aplicatie.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter

@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String producer;
    private double price;
    private int quantity;
    private String img;

    @ManyToOne
    @JoinTable(name="user_id")
    private User user;

    public Product(String name, String producer, double i,int q,String img) {
        this.name=name;
        this.producer=producer;
        this.price=i;
        this.quantity=q;
        this.img=img;
    }

    public Product(String name, String producer, double i,int q) {
        this.name=name;
        this.producer=producer;
        this.price=i;
        this.quantity=q;
    }
    public Product(){}


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return "Product: " + this.name +
                "\nPrice: " + this.price +
                "\nProducer: " + this.producer +
                "\nQuantity: " + this.quantity +
                "\nImage: " + this.img;
    }
}
