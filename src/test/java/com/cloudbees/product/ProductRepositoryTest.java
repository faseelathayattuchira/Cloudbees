package com.cloudbees.product;

import com.cloudbees.product.apimodel.ProductUpdateRequest;
import com.cloudbees.product.entity.Product;
import com.cloudbees.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void addProduct() {
        Product product = new Product("TestProduct", "TestDescription", 50.0, 100, 0.0, 10.0);

        Product addedProduct = productRepository.add(product);

        assertNotNull(addedProduct.getId());
        assertEquals(product.getName(), addedProduct.getName());
        assertEquals(product.getDescription(), addedProduct.getDescription());
        assertEquals(product.getPrice(), addedProduct.getPrice());
        assertEquals(product.getQuantityAvailable(), addedProduct.getQuantityAvailable());
        assertEquals(product.getTax(), addedProduct.getTax());
        assertEquals(product.getDiscount(), addedProduct.getDiscount());
    }


    @Test
    void findProductById() {
        Product product = new Product("TestProduct", "TestDescription", 50.0, 100, 0.0, 10.0);
        Product addedProduct = productRepository.add(product);

        Product foundProduct = productRepository.findById(addedProduct.getId());

        assertNotNull(foundProduct);
        assertEquals(addedProduct, foundProduct);
    }

    @Test
    void updateProduct() {
        Product product = new Product("TestProduct", "TestDescription", 50.0, 100, 0.0, 10.0);
        Product addedProduct = productRepository.add(product);

        ProductUpdateRequest updateRequest = new ProductUpdateRequest();
        updateRequest.setName("UpdatedProduct");
        updateRequest.setDescription("UpdatedDescription");
        updateRequest.setPrice(60.0);
        updateRequest.setQuantityAvailable(150);
        updateRequest.setTax(5.0);
        updateRequest.setDiscount(15.0);

        assertTrue(productRepository.updateProduct(addedProduct.getId(), updateRequest));

        Product updatedProduct = productRepository.findById(addedProduct.getId());

        assertNotNull(updatedProduct);
        assertEquals("UpdatedProduct", updatedProduct.getName());
        assertEquals("UpdatedDescription", updatedProduct.getDescription());
        assertEquals(60.0, updatedProduct.getPrice());
        assertEquals(150, updatedProduct.getQuantityAvailable());
        assertEquals(5.0, updatedProduct.getTax());
        assertEquals(15.0, updatedProduct.getDiscount());
    }

    @Test
    void deleteProduct() {
        Product product = new Product("TestProduct", "TestDescription", 50.0, 100, 0.0, 10.0);
        Product addedProduct = productRepository.add(product);

        assertTrue(productRepository.delete(addedProduct.getId()));

        assertNull(productRepository.findById(addedProduct.getId()));
    }
}
