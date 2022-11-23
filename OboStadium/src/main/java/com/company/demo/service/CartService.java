package com.company.demo.service;

import com.company.demo.entity.Cart;

import java.util.List;

public interface CartService {
    Long sumPrice();

    List<Cart> listCart();

    void addToCart(String idProduct);

    Cart updateQuantity(String idProduct, Integer quantity);

    void delete(String idProduct);

    void deleteAll();
}
