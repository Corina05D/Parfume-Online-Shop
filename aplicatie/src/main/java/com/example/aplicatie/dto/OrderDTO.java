package com.example.aplicatie.dto;

import java.util.List;
import java.util.Optional;

public class OrderDTO {
    private List<ProductDTO> products;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
