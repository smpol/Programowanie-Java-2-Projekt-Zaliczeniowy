package com.mprzys.projekt.services;

import com.mprzys.projekt.database.ProductDatabase;
import com.mprzys.projekt.repository.ProductDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductDatabaseService {
    private final ProductDatabaseRepository productDatabaseRepository;

    @Autowired
    public ProductDatabaseService(ProductDatabaseRepository productDatabaseRepository) {
        this.productDatabaseRepository = productDatabaseRepository;
    }

    public void addProduct(ProductDatabase productDatabase) {
        productDatabaseRepository.save(productDatabase);
    }

    public Collection<ProductDatabase> getAllProducts() {
        return productDatabaseRepository.findAll();
    }

    public String getCategoryNameById(long id) {
        return productDatabaseRepository.findById(id).get().getCategory().getNameOfCategory();
    }

    public void deleteProduct(int i) {
        productDatabaseRepository.deleteById((long) i);
    }
}