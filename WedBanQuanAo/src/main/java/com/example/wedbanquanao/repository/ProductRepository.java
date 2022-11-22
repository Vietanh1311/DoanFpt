package com.example.wedbanquanao.repository;

import com.example.wedbanquanao.entity.Product;
import com.example.wedbanquanao.model.dto.ProductInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(nativeQuery = true, name = "getListBestSellerProduct")
    public List<ProductInfoDto> getListBestSellerProduct(int limit);

//    @Query(nativeQuery = true, name = "getListNewProduct")
//    public List<ProductInfoDto> getListNewProduct(int limit);

    @Query(nativeQuery = true, name = "getRelatedProducts")
    public List<ProductInfoDto> getRelatedProducts(String id, int limit);
}

