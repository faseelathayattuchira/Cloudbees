package com.cloudbees.product.apimodel;

import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Builder
@Getter
@Setter
public class ProductRequest {
    private String name;

    private String description;

    private Double price;

    private Integer quantityAvailable;

    @Builder.Default
    private Double tax = 0.0;

    @Builder.Default
    private double discount = 0.0;
}
