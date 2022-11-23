package com.company.demo.service;

import com.company.demo.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> listOrder();

    Order checkoutOrder(Order order);

    Order findById(Long id);
}
