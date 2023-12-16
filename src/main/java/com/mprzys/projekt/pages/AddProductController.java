package com.mprzys.projekt.pages;

import com.mprzys.projekt.database.ProductDatabase;
import com.mprzys.projekt.services.ProductDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class AddProductController {

    private final ProductDatabaseService productDatabaseService;

    @Autowired
    public AddProductController(ProductDatabaseService productDatabaseService) {
        this.productDatabaseService = productDatabaseService;
    }

    @RequestMapping("/addproduct")
    public String getMainPage() {
        return "addProduct";
    }

    @PostMapping("/addproduct")
    public String addProduct(
            @RequestParam String name,
            @RequestParam long count,
            @RequestParam String price,
            @RequestParam String description,
            Model model) {

        // Tworzymy obiekt ProductDatabase na podstawie danych z formularza
        ProductDatabase productDatabase = new ProductDatabase();
        productDatabase.setNameOfProduct(name);
        productDatabase.setCountOfProduct(count);
        productDatabase.setPriceOfProduct(price);
        productDatabase.setDescriptionOfProduct(description);
        productDatabase.setDateOfAddition(LocalDateTime.now());

        // Dodajemy produkt do bazy danych
        productDatabaseService.addProduct(productDatabase);

        // Przekierowanie na stronę po dodaniu produktu (możesz dostosować)
        //return "redirect:/addproduct";
        return "redirect:/listofproducts";
    }

}
