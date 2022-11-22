package com.company.demo.service.impl;

import com.company.demo.entity.*;
import com.company.demo.exception.*;
import com.company.demo.model.dto.*;
import com.company.demo.model.mapper.*;
import com.company.demo.model.request.CreateProductReq;
import com.company.demo.repository.*;
import com.company.demo.service.*;
import com.company.demo.util.PageUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import static com.company.demo.config.Constant.*;
import java.sql.Timestamp;
import java.util.*;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Override
    public List<ProductInfoDto> getListBestSellerProduct() {
        List<ProductInfoDto> products = productRepository.getListBestSellerProduct(5);

        return promotionService.checkPublicPromotion(products);
    }



    @Override
    public DetailProductInfoDto getDetailProductById(String id) {
        // Get product info
        Optional<Product> result = productRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Sản phẩm không tồn tại");
        }

        Product product = result.get();

        if (!product.isAvailable()) {
            throw new NotFoundException("Sản phẩm không tồn tại");
        }

        DetailProductInfoDto dto = ProductMapper.toDetailProductInfoDto(product);

        // Check promotion
        Promotion promotion = promotionService.checkPublicPromotion();
        if (promotion != null) {
            dto.setCouponCode(promotion.getCouponCode());
            dto.setPromotionPrice(promotionService.calculatePromotionPrice(dto.getPrice(), promotion));
        } else {
            dto.setCouponCode("");
        }

        return dto;
    }

    @Override
    public List<ProductInfoDto> getRelatedProducts(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("Sản phẩm không tồn tại");
        }

        List<ProductInfoDto> products = productRepository.getRelatedProducts(id, 5);

        return promotionService.checkPublicPromotion(products);
    }

    @Override
    public List<Integer> getListAvailableSize(String id) {
        List<Integer> sizes = productSizeRepository.findAllSizeOfProduct(id);

        return sizes;
    }


//    @Override
//    public Product getProductById(String id) {
//        Optional<Product> product = productRepository.findById(id);
//        if (product.isEmpty()) {
//            throw new NotFoundException("Sản phẩm không tồn tại");
//        }
//
//        return product.get();
//    }


}
