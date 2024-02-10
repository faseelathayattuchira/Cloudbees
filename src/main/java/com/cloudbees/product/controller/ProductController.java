package com.cloudbees.product.controller;

import com.cloudbees.product.apimodel.ProductRequest;
import com.cloudbees.product.apimodel.ProductResponse;
import com.cloudbees.product.apimodel.ProductUpdateRequest;
import com.cloudbees.product.entity.Product;
import com.cloudbees.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("")
    public ResponseEntity<Object> getProduct(){

        try{
            List<ProductResponse> products = productService.getAllProducts();
            if(products!=null && !products.isEmpty()){
                return ResponseEntity.ok (products);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No products found");

        }catch (Exception ex){
            log.error("Exception during create operation", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("We got an error while adding this record");
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id){

        try{
            ProductResponse product =  productService.findById(id);
            if(product!=null ){
                return ResponseEntity.ok (product);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No products found");

        }catch (Exception ex){
            log.error("Exception during getting this product", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("We got an error while getting this record");
        }

    }
    @PostMapping("")
    public ResponseEntity<Object> addProduct( @RequestBody ProductRequest productRequest){
        try{

            Product prod = productService.addNewProduct(productRequest);
            return ResponseEntity.ok (prod);
        }catch (Exception ex){
            log.error("Exception during create operation", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("We got an error while adding this record:"+ex.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest request) {
        try {
            if(productService.updateProduct(id, request)){
                return ResponseEntity.ok ("Product updated: "+id);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Record not found");
        }catch (Exception ex){
            log.error("Exception during update operation", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("We got an error while adding this record");



        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        try {
            boolean deleted = productService.deleteProduct(id);
            if(deleted)
                return ResponseEntity.ok ("Product deleted: "+id);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Record not found");
        }catch (Exception ex){
            log.error("Exception during update operation", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("We got an error while deleting this record");
        }

    }
}
