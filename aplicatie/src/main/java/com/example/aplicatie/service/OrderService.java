package com.example.aplicatie.service;

import com.example.aplicatie.dto.OrderDTO;
import com.example.aplicatie.model.Order;

import java.util.List;

public interface OrderService {

    Order saveOrder(Order order);

    List<Order> readOrderList();

    void updateOrder(Order order);

    void deleteOrder(Long id);

    Order newOrder(OrderDTO order);
}
