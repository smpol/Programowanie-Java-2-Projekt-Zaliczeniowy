package com.mprzys.projekt.database;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ProductDatabase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate
    private LocalDateTime dateOfAddition;

    @NotBlank(message = "{product.not_blank}")
    private String nameOfProduct;

    @NotBlank(message = "{product.description_not_blank}")
    @Size(min = 1, max = 1000, message = "{product.description_min_max}")
    private String descriptionOfProduct;

    // Używamy adnotacji @Min z przykładową wartością dla ceny większej od 1
    // Tutaj zakładam, że cena jest przechowywana jako liczba; jeśli jest to ciąg, potrzebujesz własnego walidatora
    @Min(value = 1, message = "{product.price_higher_than_one}")
    private String priceOfProduct;

//    @NotBlank(message = "{product.category_not_blank}")
//    private String categoryOfProduct;

    @Min(value = 1, message = "{product.count_higher_than_one}")
    private long countOfProduct;

//    @NotNull(message = "{product.category_id_not_blank}")
//    private long idOfCategory;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private CategoryDatabase category;


}
