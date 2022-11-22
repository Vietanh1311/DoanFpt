package com.example.wedbanquanao.service;

import com.example.wedbanquanao.entity.Product;
import com.example.wedbanquanao.model.dto.DetailProductInfoDto;
import com.example.wedbanquanao.model.dto.ProductInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<ProductInfoDto> getListBestSellerProduct();

//    public List<ProductInfoDto> getListNewProduct();
//
    public DetailProductInfoDto getDetailProductById(String id);
//
    public Product getProductById(String id);

    public List<ProductInfoDto> getRelatedProducts(String id);

    public List<Integer> getListAvailableSize(String id);
}
