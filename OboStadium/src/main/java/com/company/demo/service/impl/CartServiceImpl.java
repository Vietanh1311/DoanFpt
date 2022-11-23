package com.company.demo.service.impl;

import com.company.demo.entity.Cart;
import com.company.demo.entity.Product;
import com.company.demo.exception.BadRequestException;
import com.company.demo.exception.NotFoundException;
import com.company.demo.repository.CartRepository;
import com.company.demo.repository.ProductRepository;
import com.company.demo.repository.ProductSizeRepository;
import com.company.demo.security.CustomUserDetails;
import com.company.demo.service.CartService;
import com.company.demo.service.ProductService;
import com.company.demo.util.CurrentUserUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductSizeRepository productSizeRepository;
    private final ProductService productService;


    @Override
    public Long sumPrice(){
        CustomUserDetails uDetailService = CurrentUserUtils.getCurrentUserUtils();
        return cartRepository.sumPrice(uDetailService.getUser().getId());
    }

    @Override
    public List<Cart> listCart(){
        CustomUserDetails uDetailService = CurrentUserUtils.getCurrentUserUtils();
        return cartRepository.findAllByUserId(uDetailService.getUser().getId());
    }


    @Override
    public void addToCart(String idProduct) {
        CustomUserDetails uDetailService = CurrentUserUtils.getCurrentUserUtils();
        if (uDetailService == null) {
            throw new BadRequestException("Bạn chưa đăng nhập");
        } else {
            Product product = productService.getProductById(idProduct);
            Cart cartOld = cartRepository.findAllByUserIdAndProductId(uDetailService.getUser().getId(), idProduct);
            System.out.println(idProduct + "sadkjahsdj");
            if (cartOld == null) {
                Cart cart = new Cart();
                cart.setName(product.getName());
                cart.setPrice(product.getPrice());
                cart.setQuantity(1);
                cart.setTotal(cart.getPrice() * cart.getQuantity());
                cart.setProductId(idProduct);
                cart.setUserId(uDetailService.getUser().getId());
                cartRepository.save(cart);
            } else {
                cartOld.setQuantity(cartOld.getQuantity() + 1);
                if (cartOld.getQuantity() > product.getQuantity()) {
                    throw new BadRequestException("Bạn chỉ có thể mua tối đa :" + product.getQuantity() + " của sản phẩm này");
                } else {
                    cartOld.setTotal(cartOld.getPrice() * cartOld.getQuantity());
                }
                cartRepository.save(cartOld);
            }

//        }
        }
    }


    @Override
    public Cart updateQuantity(String idProduct, Integer quantity) {
        CustomUserDetails uDetailService = CurrentUserUtils.getCurrentUserUtils();

        Cart cart = cartRepository.findAllByUserIdAndProductId(uDetailService.getUser().getId(), idProduct);
        if (cart == null) {
            throw new NotFoundException("người dùng chưa có sản phẩm id: " + idProduct + " trong giỏ hàng");
        }
        Product findQuantity = productRepository.findById(idProduct).get();
        if (findQuantity.getQuantity() < quantity) {
            throw new BadRequestException("Bạn chỉ có thể mua tối đa :" + findQuantity.getQuantity() + " của sản phẩm này");
        }
        cart.setQuantity(quantity);
        cart.setTotal(cart.getPrice() * cart.getQuantity());
        return cartRepository.save(cart);
    }

    @Override
    public void delete(String idProduct) {
        CustomUserDetails uDetailService = CurrentUserUtils.getCurrentUserUtils();
        Cart cart = cartRepository.findAllByUserIdAndProductId(uDetailService.getUser().getId(), idProduct);
        if (cart == null) {
            throw new NotFoundException("Không tồn tại sản phẩm: " + idProduct + " trong giỏ hàng");
        }
        cartRepository.deleteById(cart.getId());
    }

    @Override
    public void deleteAll() {
        CustomUserDetails uDetailService = CurrentUserUtils.getCurrentUserUtils();
        List<Cart> cartList =  cartRepository.findAllByUserId(uDetailService.getUser().getId());
        for(Cart cart: cartList){
            cartRepository.deleteById(cart.getId());
        }

    }



}
