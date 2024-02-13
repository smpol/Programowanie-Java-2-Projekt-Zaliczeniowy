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

    @Min(value = 1, message = "{product.price_higher_than_one}")
    private String priceOfProduct;


    @Min(value = 1, message = "{product.count_higher_than_one}")
    private long countOfProduct;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private CategoryDatabase category;


}
