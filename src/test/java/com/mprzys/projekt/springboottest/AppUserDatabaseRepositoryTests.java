package com.mprzys.projekt.springboottest;

import com.mprzys.projekt.database.AppUserDatabase;
import com.mprzys.projekt.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AppUserDatabaseRepositoryTests {

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    public void testCreateAndRetrieveUser() {
        AppUserDatabase user = new AppUserDatabase();
        user.setUsername("testUser");
        user.setPassword("testPass");
        AppUserDatabase savedUser = appUserRepository.save(user);

        Optional<AppUserDatabase> retrievedUser = appUserRepository.findById(savedUser.getId());
        assertTrue(retrievedUser.isPresent());
        assertEquals("testUser", retrievedUser.get().getUsername());
        assertEquals("testPass", retrievedUser.get().getPassword());
    }
}