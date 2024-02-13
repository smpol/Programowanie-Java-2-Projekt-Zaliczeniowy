package com.mprzys.projekt.webmvctest;

import com.mprzys.projekt.database.AppUserDatabase;
import com.mprzys.projekt.database.ProductDatabase;
import com.mprzys.projekt.pages.ListOfProductsController;
import com.mprzys.projekt.repository.AppUserRepository;
import com.mprzys.projekt.repository.ProductDatabaseRepository;
import com.mprzys.projekt.services.CategoryDatabaseService;
import com.mprzys.projekt.services.ProductDatabaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ListOfProductsController.class)
public class ListOfProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductDatabaseRepository productDatabaseRepository;

    @MockBean
    private AppUserRepository appUserRepository;

    @MockBean
    private ProductDatabaseService productDatabaseService; // Dodaj tę linijkę

    @MockBean
    private CategoryDatabaseService categoryDatabaseService; // Dodaj tę linijkę


    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setupUser() {
        AppUserDatabase appUserDatabase = new AppUserDatabase();
        appUserDatabase.setUsername("admin");
        appUserDatabase.setPassword(passwordEncoder.encode("admin"));
        appUserRepository.save(appUserDatabase);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void testListOfProductsLogged() throws Exception {
        Page<ProductDatabase> productPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);

        when(productDatabaseRepository.findAll(any(Pageable.class))).thenReturn(productPage);

        mockMvc.perform(get("/listofproducts"))
                .andExpect(status().isOk())
                .andExpect(view().name("listOfProducts"));
    }


    @Test
    public void testListOfProductsNotLogged() throws Exception {
        mockMvc.perform(get("/listofproducts"))
                .andExpect(status().isUnauthorized());
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

}
