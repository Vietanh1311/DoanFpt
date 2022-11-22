package com.company.demo.repository;

import com.company.demo.entity.Product;
import com.company.demo.model.dto.ProductInfoDto;
import com.company.demo.model.dto.ShortProductInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(nativeQuery = true, name = "getListBestSellerProduct")
    public List<ProductInfoDto> getListBestSellerProduct(int limit);


    @Query(nativeQuery = true, name = "getRelatedProducts")
    public List<ProductInfoDto> getRelatedProducts(String id, int limit);


}
