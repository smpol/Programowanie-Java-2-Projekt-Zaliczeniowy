package com.mprzys.projekt.repository;

import com.mprzys.projekt.database.ProductDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDatabaseRepository extends JpaRepository<ProductDatabase, Long> {

    public List<ProductDatabase> findAll();
}
