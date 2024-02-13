package com.mprzys.projekt.repository;

import com.mprzys.projekt.database.AppUserDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUserDatabase, Long> {
    Optional<AppUserDatabase> findByUsername(String username);

}
