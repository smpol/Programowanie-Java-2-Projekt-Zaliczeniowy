package com.mprzys.projekt.pages;

import com.mprzys.projekt.database.ProductDatabase;
import com.mprzys.projekt.repository.ProductDatabaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ListOfProductsController {
    private final ProductDatabaseRepository productDatabaseRepository;

    public ListOfProductsController(ProductDatabaseRepository productDatabaseRepository) {
        this.productDatabaseRepository = productDatabaseRepository;
    }

    @PostMapping("/deleteSelectedProducts")
    public String deleteSelectedProducts(@RequestParam(required = false) List<Long> selectedProducts) {
        if (selectedProducts == null || selectedProducts.isEmpty()) {
            return "redirect:/listofproducts"; // Nic nie rób, jeśli lista jest pusta lub null
        }

        productDatabaseRepository.deleteAllById(selectedProducts);
        return "redirect:/listofproducts";
    }

    @GetMapping("/listofproducts")
    public String showListOfProducts(Model model,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sort,
                                     @RequestParam(defaultValue = "asc") String dir) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(dir), sort));
        Page<ProductDatabase> productPage = productDatabaseRepository.findAll(pageable);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        return "listOfProducts";
    }
}
