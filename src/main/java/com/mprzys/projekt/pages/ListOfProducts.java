package com.mprzys.projekt.pages;

import com.mprzys.projekt.database.ProductDatabase;
import com.mprzys.projekt.repository.ProductDatabaseRepository;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ListOfProducts {
    private final ProductDatabaseRepository productDatabaseRepository;

    public ListOfProducts(ProductDatabaseRepository productDatabaseRepository) {
        this.productDatabaseRepository = productDatabaseRepository;
    }

    @RequestMapping("/listofproducts")
    public String showListOfProducts(Model model) {
        List<ProductDatabase> lista = productDatabaseRepository.findAll();
        model.addAttribute("products", lista);
        return "listOfProducts";
    }
}
