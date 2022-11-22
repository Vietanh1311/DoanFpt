package com.example.wedbanquanao.service.impl;

import com.example.wedbanquanao.entity.Product;
import com.example.wedbanquanao.entity.Promotion;
import com.example.wedbanquanao.exception.NotFoundException;
import com.example.wedbanquanao.model.dto.DetailProductInfoDto;
import com.example.wedbanquanao.model.dto.ProductInfoDto;
import com.example.wedbanquanao.model.mapper.ProductMapper;
import com.example.wedbanquanao.repository.ProductRepository;
import com.example.wedbanquanao.repository.ProductSizeRepository;
import com.example.wedbanquanao.service.ProductService;
import com.example.wedbanquanao.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Override
    public List<ProductInfoDto> getListBestSellerProduct() {
        List<ProductInfoDto> products = productRepository.getListBestSellerProduct(5);

        return promotionService.checkPublicPromotion(products);
    }


//    @Override
//    public List<ProductInfoDto> getListNewProduct() {
//        List<ProductInfoDto> products = productRepository.getListNewProduct(  5);
//
//        return promotionService.checkPublicPromotion(products);
//    }
//
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
    public Product getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("Sản phẩm không tồn tại");
        }

        return product.get();
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
}
