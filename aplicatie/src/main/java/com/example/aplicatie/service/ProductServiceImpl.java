package com.example.aplicatie.service;
import com.example.aplicatie.dto.ProductDTO;
import com.example.aplicatie.exporter.FileExporter;
import com.example.aplicatie.exporter.TXTFileExporter;
import com.example.aplicatie.exporter.XMLFileExporter;
import com.example.aplicatie.mapper.ProductMapper;
import com.example.aplicatie.model.Order;
import com.example.aplicatie.model.Product;
import com.example.aplicatie.model.ProductWrapper;
import com.example.aplicatie.model.User;
import com.example.aplicatie.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl<T> implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public List<ProductDTO> allProducts(){
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productResponses = new ArrayList<>();
        for(Product p : productList) {
            productResponses.add(new ProductDTO(p.getId(), p.getName(), p.getProducer(),
                    p.getPrice(), p.getQuantity(), p.getImg()));
        }
        return productResponses;
    }

    @Override
    public Optional<ProductDTO> getProduct(Long id) {
        return productRepository.findById(id).map(product -> productMapper.toDTO(product));
    }

    private ProductWrapper getLimitedProducts() {
        List<Product> p = productRepository.findAll();
        Iterator<Product> iterator = p.iterator();
        while (iterator.hasNext()) {
            Product q = iterator.next();
            if (q.getQuantity() > 10) {
                iterator.remove();
            }
        }
        return new ProductWrapper(p);
    }

    @Override
    @Transactional
    public void updateProduct(Product product){
        productRepository.findById(product.getId()).ifPresent(product1 -> {
            product1.setName(product.getName());
            product1.setProducer(product.getProducer());
            product1.setPrice(product.getPrice());
            //product1.setOrders(product.getOrders());
            productRepository.save(product1);
        });
    }

    @Override
    @Transactional
    public void deleteProduct(Long id){
        if(productRepository.findById(id).isPresent()){
            Product product=productRepository.findById(id).get();
            //List<Order> orders=product.getOrders();
//            if(orders!=null){
//                for(Order o:orders){
//                    o.getProducts().remove(product);
//                }
//            }
            productRepository.deleteById(id);
        }
    }


    @Override
    public String exportLimitedProducts(String fileType){
        FileExporter fileExporter;
        ProductWrapper p = getLimitedProducts();
        if (fileType.equals("xml")) {
            fileExporter = new XMLFileExporter();
            return fileExporter.exportData(p);
        } else if (fileType.equals("txt")) {
            fileExporter = new TXTFileExporter();
            return fileExporter.exportData(p);
        }
        return null;
    }

    public void addFavoriteProduct(User user,Product product){
        product.setUser(user);
    }

    public void deleteFavoriteProduct(User user,Product product){
        product.setUser(null);
    }
}
