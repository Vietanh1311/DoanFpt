package com.company.demo.service;

import com.company.demo.entity.Product;
import com.company.demo.entity.ProductSize;
import com.company.demo.model.dto.DetailProductInfoDto;
import com.company.demo.model.dto.PageableDto;
import com.company.demo.model.dto.ProductInfoDto;
import com.company.demo.model.dto.ShortProductInfoDto;
import com.company.demo.model.request.CreateProductReq;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProductService {
    public List<ProductInfoDto> getListBestSellerProduct();


    public DetailProductInfoDto getDetailProductById(String id);

    public List<ProductInfoDto> getRelatedProducts(String id);

    public List<Integer> getListAvailableSize(String id);

    Product updateQuantity(String productId, int quantity);

    Product getProductById(String id);
}
