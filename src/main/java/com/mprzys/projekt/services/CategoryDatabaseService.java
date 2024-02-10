package com.mprzys.projekt.services;

import com.mprzys.projekt.database.CategoryDatabase;
import com.mprzys.projekt.repository.CategoryDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryDatabaseService {

    private final CategoryDatabaseRepository categoryDatabaseRepository;

    @Autowired
    public CategoryDatabaseService(CategoryDatabaseRepository categoryDatabaseRepository) {
        this.categoryDatabaseRepository = categoryDatabaseRepository;
    }

    public void addCategory(CategoryDatabase categoryDatabase) {
        categoryDatabaseRepository.save(categoryDatabase);
    }

    public List<CategoryDatabase> getAllCategories() {
        return categoryDatabaseRepository.findAll();
    }

    public CategoryDatabase getCategoryById(long id) {
        return categoryDatabaseRepository.findById(id).orElse(null);
    }

    public CategoryDatabase getCategoryByName(String name) {
        return categoryDatabaseRepository.findBynameOfCategory(name);
    }


}
