package com.mprzys.projekt.springboottest;

import com.mprzys.projekt.database.CategoryDatabase;
import com.mprzys.projekt.services.CategoryDatabaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CategoryDatabaseServiceIntegrationTest {

    @Autowired
    private CategoryDatabaseService categoryDatabaseService;

    @Test
    public void testAddCategory() {
        // Stworzenie nowej kategorii
        CategoryDatabase newCategory = new CategoryDatabase();
        newCategory.setNameOfCategory("Elektronika");
        newCategory.setDescriptionOfCategory("Wszystko związane z elektroniką");

        // Dodanie kategorii i sprawdzenie wyniku
        categoryDatabaseService.addCategory(newCategory);

        // Pobranie dodanej kategorii z bazy danych
        CategoryDatabase savedCategory = categoryDatabaseService.getCategoryByName("Elektronika");

        // Weryfikacja, że kategoria została pomyślnie dodana
        assertNotNull(savedCategory);
        assertEquals("Elektronika", savedCategory.getNameOfCategory());
        assertEquals("Wszystko związane z elektroniką", savedCategory.getDescriptionOfCategory());
    }
}