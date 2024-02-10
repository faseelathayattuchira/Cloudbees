package com.cloudbees.product.service;

import com.cloudbees.product.apimodel.ProductRequest;
import com.cloudbees.product.apimodel.ProductResponse;
import com.cloudbees.product.apimodel.ProductUpdateRequest;
import com.cloudbees.product.entity.Product;
import com.cloudbees.product.repository.ProductRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    ProductRepository productRepo;

    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepo.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse productResponse = product.toProductResponse();
            productResponses.add(productResponse);
        }

        return productResponses;

    }

    public ProductResponse findById(Long id){
        Product product = productRepo.findById(id);
        if(product==null){
            return null;
        }
        return product.toProductResponse();

    }

    public Product addNewProduct(ProductRequest request) throws Exception{

        if(request.getName()==null || request.getName().isEmpty()){
            throw new Exception("name cannot be null or empty");
        }
        if(request.getDescription()==null || request.getDescription().isEmpty()){
            throw new Exception("description cannot be null or empty");
        }
        if(request.getPrice()==null || request.getPrice()<0){
            throw new Exception("price cannot be null or less than zero");
        }
        if(request.getQuantityAvailable()==null || request.getQuantityAvailable()<=0){
            throw new Exception("Quantity cannot be null and it should be positive");
        }

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantityAvailable(request.getQuantityAvailable())
                .tax(request.getTax())
                .discount(request.getDiscount())
                .build();


        // Save the product to the database
        return productRepo.add(product);

    }

    public boolean updateProduct(Long id, ProductUpdateRequest request) {
        return productRepo.updateProduct(id, request);

    }


    public boolean deleteProduct(Long id) {
        return productRepo.delete(id);
    }
}
