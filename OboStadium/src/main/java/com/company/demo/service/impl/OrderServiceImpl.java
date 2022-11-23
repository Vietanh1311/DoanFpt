package com.company.demo.service.impl;

import com.company.demo.entity.Cart;
import com.company.demo.entity.Order;
import com.company.demo.entity.OrderDetail;
import com.company.demo.entity.Product;
import com.company.demo.repository.CartRepository;
import com.company.demo.repository.OrderDetailRepository;
import com.company.demo.repository.OrderRepository;
import com.company.demo.security.CustomUserDetails;
import com.company.demo.service.OrderService;
import com.company.demo.service.ProductService;
import com.company.demo.util.CurrentUserUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final OrderDetailRepository orderDetailRepository;


    // List order theo user nguoi dang nhap
    @Override
    public List<Order> listOrder(){
        CustomUserDetails uDetailService = CurrentUserUtils.getCurrentUserUtils();
        return orderRepository.findAllByAccountId(uDetailService.getUser().getId());
    }


    @Override
    public Order checkoutOrder(Order order)  {
        CustomUserDetails uDetailService = CurrentUserUtils.getCurrentUserUtils();

        Long total_amount = cartRepository.sumPrice(uDetailService.getUser().getId());
        Integer quantity_cart = cartRepository.sumQuantity(uDetailService.getUser().getId());
        System.out.println(total_amount + "total_order");
        if (uDetailService.getUsername() != null) {
            order.setShipping(40000f);
            order.setStatus(1);
            order.setDiscount(0.0f);
            order.setQuantity(quantity_cart);
            order.setGrandTotal((long) (total_amount + order.getShipping() - order.getDiscount()));
            order.setAccountId(uDetailService.getUser().getId());
            orderRepository.save(order);
        }

        Collection<Cart> listCartItem = cartRepository.findAllByUserId(uDetailService.getUser().getId());
        for (Cart cartItem : listCartItem) {
            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setPrice(cartItem.getPrice());
            orderDetail.setQuantity(cartItem.getQuantity());

            Double price = Double.parseDouble(orderDetail.getPrice().toString());
            orderDetail.setTotal(orderDetail.getPrice() * orderDetail.getQuantity());

            orderDetail.setImage(cartItem.getImage());
            orderDetail.setProductId(cartItem.getProductId());
            orderDetail.setOrderId(order.getId());
            orderDetail.setUserId(uDetailService.getUser().getId());

            orderDetailRepository.save(orderDetail);
            Product productEntity = productService.getProductById(cartItem.getProductId());

            int quantityOrderDetail = orderDetail.getQuantity();
            int quantityProduct = productEntity.getQuantity();

            int updateQuantity = quantityProduct - quantityOrderDetail;
            productService.updateQuantity(productEntity.getId(), updateQuantity);
            // xoa cart
            cartRepository.deleteById(cartItem.getId());
        }

        return order;
    }

    @Override
    public Order findById(Long id){
        return orderRepository.findById(id).get();
    }



}
