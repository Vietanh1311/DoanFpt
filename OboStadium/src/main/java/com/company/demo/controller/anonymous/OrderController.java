package com.company.demo.controller.anonymous;

import com.company.demo.entity.Order;
import com.company.demo.service.CartService;
import com.company.demo.service.OrderService;
import com.company.demo.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private final PaymentService paymentService;

    @GetMapping("/checkout")
    public String formOrder(Model model){
        model.addAttribute("listPayment",paymentService.listPayment());
        model.addAttribute("order", new Order());
        model.addAttribute("sumPrice",cartService.sumPrice());
        model.addAttribute("listCart",cartService.listCart());
        return "order/checkout";
    }

    @GetMapping("/list")
    public String listOrder(
            Model model
    ){
        model.addAttribute("listOrder",orderService.listOrder());
        System.out.println(orderService.listOrder());
        return "order/list";
    }


    @GetMapping("/get-one")
    public String getOnePayment(
            Model model,
            @RequestParam(name = "paymentId") Long paymentId
    ){
//        model.addAttribute("")
        return "order/checkout";
    }

    @PostMapping("/checkout")
    public String create(@Valid @ModelAttribute Order order, BindingResult result){
        orderService.checkoutOrder(order);
        return "order/checkout";
    }

//    @GetMapping("/order/list")
//    public String orderList(Model model, HttpServletRequest request){
//        String username = request.getRemoteUser();
//        model.addAttribute("orders",orderService.findByUsername(username));
//        return "user/order/list";
//    }


    @GetMapping("/detail/{id}")
    public String orderDetail(Model model, @PathVariable("id") Long id){
        model.addAttribute("order",orderService.findById(id));
        System.out.println(orderService.findById(id) + "dsakdjaskdkas");
        return "order/order-detail";
    }


}
