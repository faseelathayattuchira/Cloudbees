package com.cloudbees.product.repository;

import com.cloudbees.product.apimodel.ProductUpdateRequest;
import com.cloudbees.product.entity.Product;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductRepository  {
    public static Map<Long, Product> map = new HashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);


    public Product add(Product product) {
        Long productId = generateProductId();
        product.setId(productId);
        map.put(productId, product);
        return product;
    }
    private Long generateProductId() {
        return idGenerator.getAndIncrement();
    }
    public List<Product> findAll() {
        return new ArrayList<>(map.values());
    }

    public Product findById(Long productId) {
        return map.get(productId);
    }


    public boolean updateProduct(Long id, ProductUpdateRequest request) {
        Product p = findById(id);
        if(p==null){
            return false;
        }
        if (request.getName() != null) {
            p.setName(request.getName());
        }

        if (request.getDescription() != null) {
            p.setDescription(request.getDescription());
        }

        if (request.getPrice() != null) {
            p.setPrice(request.getPrice());
        }

        if (request.getQuantityAvailable() != null) {
            p.setQuantityAvailable(request.getQuantityAvailable());
        }

        if (request.getTax() != null) {
            p.setTax(request.getTax());
        }

        if (request.getDiscount() != null) {
            p.setDiscount(request.getDiscount());
        }
        return true;
    }


    public boolean delete(Long id) {
        if(!map.containsKey(id)){
            return false;
        }
        map.remove(id);
        return true;
    }
}
