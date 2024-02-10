package com.mprzys.projekt.repository;

import com.mprzys.projekt.database.CategoryDatabase;
import com.mprzys.projekt.database.ProductDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDatabaseRepository extends JpaRepository<CategoryDatabase, Long> {
    CategoryDatabase findBynameOfCategory(String name);
}
