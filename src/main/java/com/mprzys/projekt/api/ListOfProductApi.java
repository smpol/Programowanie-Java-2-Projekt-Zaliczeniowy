package com.mprzys.projekt.api;

import com.mprzys.projekt.repository.ProductDatabaseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ListOfProductApi {

    private final ProductDatabaseRepository productDatabaseRepository;

    public ListOfProductApi(ProductDatabaseRepository productDatabaseRepository) {
        this.productDatabaseRepository = productDatabaseRepository;
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<Map<String, Object>>> getAllProducts() {
        List<Map<String, Object>> products = productDatabaseRepository.findAll().stream()
                .map(product -> {
                    Map<String, Object> productMap = new HashMap<>();
                    productMap.put("id", product.getId());
                    productMap.put("dateOfAddition", product.getDateOfAddition());
                    productMap.put("nameOfProduct", product.getNameOfProduct());
                    productMap.put("descriptionOfProduct", product.getDescriptionOfProduct());
                    productMap.put("priceOfProduct", product.getPriceOfProduct());
                    productMap.put("countOfProduct", product.getCountOfProduct());
                    productMap.put("category", product.getCategory() != null ? product.getCategory().getId() : null);
                    return productMap;
                }).collect(Collectors.toList());

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/api/test")
    public String test() {
        return "test";
    }

}
