package com.mprzys.projekt.webmvctest;

import com.mprzys.projekt.pages.LoginController;
import com.mprzys.projekt.repository.AppUserRepository;
import com.mprzys.projekt.repository.ProductDatabaseRepository;
import com.mprzys.projekt.services.CategoryDatabaseService;
import com.mprzys.projekt.services.ProductDatabaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
public class RegisterAndLoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductDatabaseRepository productDatabaseRepository;

    @MockBean
    private AppUserRepository appUserRepository;

    @MockBean
    private ProductDatabaseService productDatabaseService;
    @MockBean
    private CategoryDatabaseService categoryDatabaseService;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testRegisterPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk());
    }

}
