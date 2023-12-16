package com.mprzys.projekt.repository;

import com.mprzys.projekt.database.ProductDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDatabaseRepository extends JpaRepository<ProductDatabase, Long> {
}
