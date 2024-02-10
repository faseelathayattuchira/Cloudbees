package com.cloudbees.product.apimodel;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {

    private String name;

    private String description;
    private Double price;
    private Integer quantityAvailable;
    private Double tax;
    private Double discount;
}
