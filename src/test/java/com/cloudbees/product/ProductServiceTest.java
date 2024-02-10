package com.cloudbees.product;

import com.cloudbees.product.apimodel.ProductRequest;
import com.cloudbees.product.apimodel.ProductUpdateRequest;
import com.cloudbees.product.entity.Product;
import com.cloudbees.product.repository.ProductRepository;
import com.cloudbees.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        // Mock the behavior of productRepo.findAll()
        when(productRepo.findAll()).thenReturn(getMockProducts());

        // Call the actual method from the service
        var result = productService.getAllProducts();

        // Assert the result
        assertNotNull(result);
        assertEquals(2, result.size()); // Assuming getMockProducts() returns a list with 2 products
    }

    @Test
    void findById() {
        // Mock the behavior of productRepo.findById()
        when(productRepo.findById(1L)).thenReturn(getMockProduct());

        // Call the actual method from the service
        var result = productService.findById(1L);

        // Assert the result
        assertNotNull(result);
        assertEquals("MockProduct", result.getName());
    }

    @Test
    void addNewProduct() throws Exception {
        // Mock the behavior of productRepo.add()
        when(productRepo.add(any())).thenReturn(getMockProduct());

        // Create a mock ProductRequest
        var request = ProductRequest.builder()
                .name("MockProduct")
                .description("MockDescription")
                .price(100.0)
                .quantityAvailable(50)
                .build();

        // Call the actual method from the service
        var result = productService.addNewProduct(request);

        // Assert the result
        assertNotNull(result);
        assertEquals("MockProduct", result.getName());
    }

    @Test
    void updateProduct() {
        // Mock the behavior of productRepo.updateProduct()
        when(productRepo.updateProduct(anyLong(), any())).thenReturn(true);

        // Call the actual method from the service
        var result = productService.updateProduct(1L, new ProductUpdateRequest());

        // Assert the result
        assertTrue(result);
    }

    @Test
    void deleteProduct() {
        // Mock the behavior of productRepo.delete()

        when(productRepo.delete(1L)).thenReturn(true);


        // Call the actual method from the service
        var result = productService.deleteProduct(1L);


        // Assert the result
        assertEquals(productService.getAllProducts().size(),0);
        assertTrue(result);
    }

    // Helper method to return a list of mock products
    private List<Product> getMockProducts() {
        List<Product> products = new ArrayList<>();
        products.add(getMockProduct());
        products.add(getMockProduct());
        return products;
    }

    // Helper method to return a mock product
    private Product getMockProduct() {
        return Product.builder()
                .id(1L)
                .name("MockProduct")
                .description("MockDescription")
                .price(100.0)
                .quantityAvailable(50)
                .build();
    }
}
