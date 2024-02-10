package com.cloudbees.product.entity;

import com.cloudbees.product.apimodel.ProductResponse;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Builder
@Data
@Slf4j
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;

    private String name;


    private String description;


    private double price;

    private double tax;

    private double discount;

    private int quantityAvailable;

    public Product(String name, String description, double price, int quantityAvailable, double tax, double discount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.tax = tax;
        this.discount = discount;
    }
    public ProductResponse toProductResponse() {
        double finalPrice = this.findFinalPrice();
        return ProductResponse.builder()
                .productId(this.id)
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .quantityAvailable(this.quantityAvailable)
                .tax(this.tax)
                .discount(this.discount)
                .finalPrice(finalPrice)
                .build();
    }

    private double findFinalPrice() {
        double finalPrice = this.price;


            finalPrice -= finalPrice * (this.discount / 100);


            finalPrice += finalPrice * (this.tax / 100);

        log.info("finalPrice:"+finalPrice);
            return finalPrice;

    }
}
