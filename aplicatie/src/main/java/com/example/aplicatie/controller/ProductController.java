package com.example.aplicatie.controller;

import com.example.aplicatie.dto.ProductDTO;
import com.example.aplicatie.service.ProductService;
import com.example.aplicatie.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/produse")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.allProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){
        Optional<ProductDTO> productDTOOptional = productService.getProduct(id);
        if(productDTOOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(productDTOOptional.get());
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/export")
    public ResponseEntity<?> exportProducts(@RequestParam String fileType){
        return ResponseEntity.status(HttpStatus.OK).body(productService.exportLimitedProducts(fileType));
    }
}
