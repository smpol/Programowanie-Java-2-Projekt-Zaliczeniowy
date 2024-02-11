package com.mprzys.projekt.springboottest;

import com.mprzys.projekt.database.AppUser;
import com.mprzys.projekt.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AppUserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateAndRetrieveUser() {
        AppUser user = new AppUser();
        user.setUsername("testUser");
        user.setPassword("testPass");
        AppUser savedUser = userRepository.save(user);

        Optional<AppUser> retrievedUser = userRepository.findById(savedUser.getId());
        assertTrue(retrievedUser.isPresent());
        assertEquals("testUser", retrievedUser.get().getUsername());
        assertEquals("testPass", retrievedUser.get().getPassword());
    }
}