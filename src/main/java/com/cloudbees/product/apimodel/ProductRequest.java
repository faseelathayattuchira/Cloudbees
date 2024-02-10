package com.cloudbees.product.apimodel;

import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Builder
@Getter
@Setter
public class ProductRequest {
    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "description cannot be null")
    private String description;

    @NotNull(message = "Price cannot be null")
    @PositiveOrZero(message = "Price must be a positive number")
    private Double price;

    @NotNull(message = "Quantity available cannot be null")
    @Positive(message = "Quantity available must be a positive number")
    private Integer quantityAvailable;

    @Builder.Default
    private Double tax = 0.0;

    @Builder.Default
    private double discount = 0.0;
}
