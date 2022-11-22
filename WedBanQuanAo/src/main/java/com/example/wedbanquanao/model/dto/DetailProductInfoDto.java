package com.example.wedbanquanao.model.dto;

import com.example.wedbanquanao.entity.Brand;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailProductInfoDto {
    private String id;

    private String name;

    private String slug;

    private long price;

    private int totalSold;

    private ArrayList<String> productImages;

    private ArrayList<String> onfeetImages;

    private long promotionPrice;

    private String couponCode;

    private String description;

    private Brand brand;
}
