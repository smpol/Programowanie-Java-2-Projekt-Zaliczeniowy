package com.mprzys.projekt.pages;

import com.mprzys.projekt.database.ProductDatabase;
import com.mprzys.projekt.services.CategoryDatabaseService;
import com.mprzys.projekt.services.ProductDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/addproduct") // Użyj RequestMapping na poziomie klasy dla lepszej organizacji
public class AddProductController {

    private final ProductDatabaseService productDatabaseService;
    private final CategoryDatabaseService categoryDatabaseService;


    @Autowired
    public AddProductController(ProductDatabaseService productDatabaseService, CategoryDatabaseService categoryDatabaseService) {
        this.productDatabaseService = productDatabaseService;
        this.categoryDatabaseService = categoryDatabaseService;
    }


    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("product", new ProductDatabase()); // Dodaj pusty produkt do modelu, aby uniknąć błędów null w formularzu
        model.addAttribute("categories", categoryDatabaseService.getAllCategories()); // Dodaj wszystkie kategorie do modelu (dla listy rozwijanej w formularzu
        return "addProduct";
    }


    @PostMapping
    public String addProduct(@Valid @ModelAttribute("product") ProductDatabase productDatabase, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors()); // Wyświetl błędy walidacji w konsoli
            model.addAttribute("categories", categoryDatabaseService.getAllCategories());
            return "addProduct"; // W przypadku błędów walidacji, użytkownik zostanie przekierowany z powrotem do formularza
        }

        productDatabase.setDateOfAddition(LocalDateTime.now()); // Ustaw aktualną datę dodania
        productDatabaseService.addProduct(productDatabase); // Zapisz produkt do bazy danych

        return "redirect:/listofproducts"; // Przekieruj na listę produktów po pomyślnym dodaniu
    }
}
