package com.example.wedbanquanao.service;

import com.example.wedbanquanao.entity.Promotion;
import com.example.wedbanquanao.model.dto.ProductInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionService {
    public List<ProductInfoDto> checkPublicPromotion(List<ProductInfoDto> products);

    public Promotion checkPublicPromotion();

//    public Promotion checkPromotion(String code);
//
//    public Promotion getPromotionById(long id);
//
    public long calculatePromotionPrice(Long price, Promotion promotion);

}
