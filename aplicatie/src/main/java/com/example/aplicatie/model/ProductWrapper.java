package com.example.aplicatie.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "products")
public class ProductWrapper{

    private List<Product> products;

    public ProductWrapper() {
        // Required default constructor
    }

    public ProductWrapper(List<Product> products) {
        this.products = products;
    }

    @XmlElement(name = "product")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de produse:\n");
        for (Product product : this.products) {
            sb.append(product.toString()).append("\n");
        }
        return sb.toString();
    }
}
