package com.company.demo.controller.anonymous;

import com.company.demo.entity.Cart;
import com.company.demo.service.CartService;
import com.company.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/list")
    public String findAll(
            Model model
    ) {
        model.addAttribute("listCart", cartService.listCart());
        model.addAttribute("sumPrice",cartService.sumPrice());
        System.out.println(cartService.listCart());
        return "cart/cart-view";
    }




    @GetMapping("/add-cart/{id}")
    public String createCart(
            Model model,
            @PathVariable("id") String idProduct
    ) {
        cartService.addToCart(idProduct);
        return "redirect:/cart/list";
    }

    @RequestMapping(value = "/update-cart/{id}")
    public ModelAndView updateQuantity(
            ModelMap model,
            @PathVariable("id") String idProduct,
            @RequestParam(name = "quantity") Integer quantity
    ) {
        cartService.updateQuantity(idProduct, quantity);
        return new ModelAndView("redirect:/cart/list", model);
    }

    @GetMapping("/clear")
    public ModelAndView clearCart(
            ModelMap model
    ) {
        cartService.deleteAll();
        return new ModelAndView("redirect:/cart/list", model);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(
            ModelMap model,
            @PathVariable("id") String idProduct
    ) {
        cartService.delete(idProduct);
        return new ModelAndView("forward:/cart/list", model);
    }


}
