package com.cloudbees.product.apimodel;

import lombok.*;

@Builder
@Getter
@Setter
public class ProductResponse {
    private Long productId;
    private String name;

    private String description;
    private double price;
    private int quantityAvailable;
    private Double tax;
    private double discount;

    private double finalPrice;
}
