package com.example.aplicatie.service;

import com.example.aplicatie.dto.OrderDTO;
import com.example.aplicatie.dto.ProductDTO;
import com.example.aplicatie.model.Order;
import com.example.aplicatie.model.Product;
import com.example.aplicatie.repository.OrderRepository;
import com.example.aplicatie.repository.ProductRepository;
import com.example.aplicatie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public List<Order> readOrderList() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        AtomicReference<Double> totalPrice = new AtomicReference<>((double) 0);
        orderRepository.findById(order.getId()).ifPresent(order1 -> {
            order1.setUser(order.getUser());
            order1.setProducts(order.getProducts());
            List<Product> products = order.getProducts();
            for (Product p : products)
                totalPrice.set(totalPrice.get() + p.getPrice());
            orderRepository.save(order1);
        });
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        if (orderRepository.findById(id).isPresent()) {
            Order order = orderRepository.findById(id).get();
            List<Product> products = order.getProducts();
            for (Product p : products) {
                // p.getOrders().remove(order);
            }
            orderRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public Order newOrder(OrderDTO order) {

        Order o = new Order();
        List<Product> products = new ArrayList<>();
        List<ProductDTO> l = order.getProducts();
        for (ProductDTO q : l) {
            Optional<Product> optionalProduct = productRepository.findById(q.getId());
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                products.add(product);
            }
        }
        o.setUser(userRepository.findById(252L).get());
        o.setProducts(products);
        return orderRepository.save(o);
    }
}
