package com.mprzys.projekt.api.webmvctest;

import com.mprzys.projekt.api.ListOfProductApi;
import com.mprzys.projekt.database.ProductDatabase;
import com.mprzys.projekt.repository.ProductDatabaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ListOfProductApi.class)
public class ListOfProductApiWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductDatabaseRepository productDatabaseRepository;

    private ProductDatabase product;

    @BeforeEach
    public void setUp() {
        product = new ProductDatabase();
        product.setId(1L);
        product.setNameOfProduct("Kubek");
        product.setDescriptionOfProduct("Kubek z logo firmy");
        product.setPriceOfProduct("10");
        product.setDateOfAddition(LocalDateTime.now());
        product.setCountOfProduct(10);
    }

    @Test
    @WithMockUser
    public void shouldReturnListOfProducts() throws Exception {
        List<ProductDatabase> allProducts = Arrays.asList(product);

        given(productDatabaseRepository.findAll()).willReturn(allProducts);

        mockMvc.perform(get("/api/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'nameOfProduct':'Kubek'}]"));
    }
}
