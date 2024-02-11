package com.mprzys.projekt.webmvctest;

import com.mprzys.projekt.database.ProductDatabase;
import com.mprzys.projekt.pages.ListOfProducts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import com.mprzys.projekt.repository.ProductDatabaseRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.Collections;


@WebMvcTest(ListOfProducts.class)
public class ListOfProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductDatabaseRepository productDatabaseRepository;

    @MockBean
    private ListOfProducts listOfProducts;


    @Test
    @WithMockUser
    public void testListProducts() throws Exception {
        Page<ProductDatabase> productPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);

        when(productDatabaseRepository.findAll(any(Pageable.class))).thenReturn(productPage);

        mockMvc.perform(get("/listofproducts"))
                .andExpect(status().isOk())
                .andExpect(view().name("listOfProducts"));
    }
}
