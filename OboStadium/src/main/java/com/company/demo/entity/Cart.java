package com.company.demo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "carts")
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private long price;

    private long total;

    private int quantity;

    private String image;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("user_id")
    private Long userId;

}
