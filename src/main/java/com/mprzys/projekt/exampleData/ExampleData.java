package com.mprzys.projekt.exampleData;

import com.mprzys.projekt.database.AppUser;
import com.mprzys.projekt.database.CategoryDatabase;
import com.mprzys.projekt.database.ProductDatabase;
import com.mprzys.projekt.repository.UserRepository;
import com.mprzys.projekt.services.CategoryDatabaseService;
import com.mprzys.projekt.services.ProductDatabaseService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ExampleData {

    @Autowired
    private ProductDatabaseService productDatabaseService;

    @Autowired

    private CategoryDatabaseService categoryDatabaseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Boolean started = false;

    @PostConstruct
    public void init() {
        // Tworzenie i dodawanie kategorii "Kuchnia"
        if (categoryDatabaseService.getAllCategories().isEmpty() && !started) {
            CategoryDatabase categoryKitchen = new CategoryDatabase();
            categoryKitchen.setNameOfCategory("Kuchnia");
            categoryKitchen.setDescriptionOfCategory("Produkty do kuchni");
            categoryDatabaseService.addCategory(categoryKitchen);

            // Tworzenie i dodawanie kategorii "Łazienka"
            CategoryDatabase categoryBathroom = new CategoryDatabase();
            categoryBathroom.setNameOfCategory("Łazienka");
            categoryBathroom.setDescriptionOfCategory("Produkty do łazienki");
            categoryDatabaseService.addCategory(categoryBathroom);
        }

        // Dodawanie produktów, jeśli baza danych jest pusta
        if (productDatabaseService.getAllProducts().isEmpty() && !started) {
            ProductDatabase product = new ProductDatabase();
            product.setNameOfProduct("Kubek");
            product.setDescriptionOfProduct("Kubek z logo firmy");
            product.setPriceOfProduct("10");
            // Ustawienie kategorii dla produktu
            product.setCategory(categoryDatabaseService.getCategoryById(1));
            product.setDateOfAddition(LocalDateTime.now());
            product.setCountOfProduct(10);
            productDatabaseService.addProduct(product);
        }
        //Dodanie przykladowego uzytkownika do bazy danych jesli jest pusta
        if (!started) {
            AppUser appUser = new AppUser();
            appUser.setUsername("admin");
            appUser.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(appUser);
        }


        started = true;
    }

}
