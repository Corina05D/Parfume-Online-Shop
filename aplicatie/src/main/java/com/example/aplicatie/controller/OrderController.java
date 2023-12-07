package com.example.aplicatie.controller;

import com.example.aplicatie.dto.OrderDTO;
import com.example.aplicatie.model.Order;
import com.example.aplicatie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.newOrder(orderDTO));
    }
}
