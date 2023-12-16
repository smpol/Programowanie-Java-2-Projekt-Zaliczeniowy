package com.mprzys.projekt.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class ProductDatabase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate
    private LocalDateTime dateOfAddition;

    private String nameOfProduct;
    private String descriptionOfProduct;
    private String priceOfProduct;
    private String categoryOfProduct;
    private long countOfProduct;
}
