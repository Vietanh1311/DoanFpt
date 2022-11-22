package com.company.demo.controller.anonymous;

import com.company.demo.exception.NotFoundException;
import com.company.demo.model.dto.DetailProductInfoDto;
import com.company.demo.model.dto.ProductInfoDto;
import com.company.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.company.demo.config.Constant.*;

import java.util.List;

@Controller
public class ShopController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private ProductService productService;



    @GetMapping("/")
    public String getIndexPage(Model model) {
        // Get new products
//        List<ProductInfoDto> newProducts = productService.getListNewProduct();
//        model.addAttribute("newProducts", newProducts);

        // Get best seller products
        List<ProductInfoDto> bestSellerProducts = productService.getListBestSellerProduct();
        model.addAttribute("bestSellerProducts", bestSellerProducts);

        // Get suggest products
//        List<ProductInfoDto> suggestProducts = productService.getListSuggestProduct();
//        model.addAttribute("suggestProducts", suggestProducts);
//
//        // Get latest posts
//        List<Post> latestPosts = blogService.getLatestPost();
//        model.addAttribute("latestPosts", latestPosts);

        return "shop/index";
    }



    @GetMapping("/san-pham/{slug}/{id}")
    public String getDetailProductPage(Model model, @PathVariable String id) {
        // Get detail info
        DetailProductInfoDto product;
        try {
            product = productService.getDetailProductById(id);
        } catch (NotFoundException ex) {
            return "error/404";
        } catch (Exception ex) {
            return "error/500";
        }
        model.addAttribute("product", product);

        // Get related products
        List<ProductInfoDto> relatedProducts = productService.getRelatedProducts(id);
        model.addAttribute("relatedProducts", relatedProducts);

        // Get list available size
        List<Integer> availableSizes = productService.getListAvailableSize(id);
        model.addAttribute("availableSizes", availableSizes);
        if (availableSizes.size() > 0) {
            model.addAttribute("canBuy", true);
        } else {
            model.addAttribute("canBuy", false);
        }

        // Render list size
        model.addAttribute("sizeVn", SIZE_VN);
        model.addAttribute("sizeUs", SIZE_US);
        model.addAttribute("sizeCm", SIZE_CM);

        return "shop/detail";
    }

//    @GetMapping("/dat-hang/{id}/{size}")
//    public String getCartPage(Model model, @PathVariable String id, @PathVariable int size) {
//        // Get detail info
//        DetailProductInfoDto product;
//        try {
//            product = productService.getDetailProductById(id);
//        } catch (NotFoundException ex) {
//            return "error/404";
//        } catch (Exception ex) {
//            return "error/500";
//        }
//        model.addAttribute("product", product);
//
//        // Validate size
//        if (size < 35 || size > 42) {
//            return "error/404";
//        }
//
//        // Get list available size
//        List<Integer> availableSizes = productService.getListAvailableSize(id);
//        model.addAttribute("availableSizes", availableSizes);
//        boolean notFoundSize = true;
//        for (Integer availableSize : availableSizes) {
//            if (availableSize == size) {
//                notFoundSize = false;
//                break;
//            }
//        }
//        model.addAttribute("notFoundSize", notFoundSize);
//
//        // Render list size
//        model.addAttribute("sizeVn", SIZE_VN);
//        model.addAttribute("sizeUs", SIZE_US);
//        model.addAttribute("sizeCm", SIZE_CM);
//
//        model.addAttribute("size", size);
//
//        return "shop/payment";
//    }


//    @PostMapping("/api/order")
//    public ResponseEntity<?> createOrder(@Valid @RequestBody CreateOrderReq orderReq) {
//        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
//
//        Order order = orderService.createOrder(orderReq, user.getId());
//
//        return ResponseEntity.ok(order.getId());
//    }
//    @GetMapping("/api/muahang/{id}")
//    public String getmuahang(Model model , @PathVariable String id) {
//        int size = 38;
//        DetailProductInfoDto product;
//        product = productService.getDetailProductById(id);
//
//        model.addAttribute("product", product);
//        if (size < 35 || size > 42) {
//            return "error/404";
//        }
//
//        // Get list available size
//        List<Integer> availableSizes = productService.getListAvailableSize(id);
//        model.addAttribute("availableSizes", availableSizes);
//        boolean notFoundSize = true;
//        for (Integer availableSize : availableSizes) {
//            if (availableSize == size) {
//                notFoundSize = false;
//                break;
//            }
//        }
//        model.addAttribute("notFoundSize", notFoundSize);
//
//        // Render list size
//        model.addAttribute("sizeVn", SIZE_VN);
//        model.addAttribute("sizeUs", SIZE_US);
//        model.addAttribute("sizeCm", SIZE_CM);
//
//        model.addAttribute("size", size);
//        return "shop/muahang";
//    }
}
