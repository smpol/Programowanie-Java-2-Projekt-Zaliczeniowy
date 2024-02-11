package com.mprzys.projekt.springboottest;

import com.mprzys.projekt.database.ProductDatabase;
import com.mprzys.projekt.repository.ProductDatabaseRepository;
import com.mprzys.projekt.services.CategoryDatabaseService;
import com.mprzys.projekt.services.ProductDatabaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class ProductDatabaseServiceIntegrationTest {

    @Autowired
    private ProductDatabaseService service;

    @MockBean
    private ProductDatabaseRepository productRepository; // Załóżmy, że to jest interfejs do operacji na bazie danych.

    @Autowired
    private CategoryDatabaseService categoryDatabaseService;

    @Test
    public void testAddProduct() {
        ProductDatabase product = new ProductDatabase();
        product.setNameOfProduct("Kubek");
        product.setDescriptionOfProduct("Kubek z logo firmy");
        product.setPriceOfProduct("10");
        // Ustawienie kategorii dla produktu
        product.setCategory(categoryDatabaseService.getCategoryById(1));
        product.setDateOfAddition(LocalDateTime.now());
        product.setCountOfProduct(10);
        service.addProduct(product);


    }

    @Test
    public void deleteProduct() {
        service.deleteProduct(1);
        verify(productRepository).deleteById(1L);
    }
}