package com.example.aplicatie.service;
import com.example.aplicatie.dto.ProductDTO;
import com.example.aplicatie.model.Product;
import com.example.aplicatie.model.User;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product saveProduct(Product product);

    List<ProductDTO> allProducts();

    Optional<ProductDTO> getProduct(Long id);

    void updateProduct(Product product);

    void deleteProduct(Long id);

    String exportLimitedProducts(String fileType);
}
