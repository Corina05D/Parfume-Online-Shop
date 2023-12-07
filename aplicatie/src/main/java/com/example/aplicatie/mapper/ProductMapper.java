package com.example.aplicatie.mapper;

import com.example.aplicatie.dto.ProductDTO;
import com.example.aplicatie.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO toDTO(Product product){
        ProductDTO productDTO = new ProductDTO(
                product.getId(),
                product.getName(),
                product.getProducer(),
                product.getPrice(),
                product.getQuantity(),
                product.getImg()
        );
        return productDTO;
    }
}
