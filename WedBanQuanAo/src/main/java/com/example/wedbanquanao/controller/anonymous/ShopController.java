package com.example.wedbanquanao.controller.anonymous;

import com.example.wedbanquanao.exception.NotFoundException;
import com.example.wedbanquanao.model.dto.DetailProductInfoDto;
import com.example.wedbanquanao.model.dto.ProductInfoDto;
import com.example.wedbanquanao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static com.example.wedbanquanao.config.Constant.*;

@Controller
public class ShopController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getIndexPage(Model model) {
        List<ProductInfoDto> bestSellerProducts = productService.getListBestSellerProduct();
        model.addAttribute("bestSellerProducts", bestSellerProducts);

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
}
