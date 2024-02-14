package com.mprzys.projekt.api.springboottests;

import com.mprzys.projekt.database.ProductDatabase;
import com.mprzys.projekt.repository.ProductDatabaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListOfProductApiSpringBootTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductDatabaseRepository productDatabaseRepository;

    @BeforeEach
    public void setUp() {
        // Czyszczenie bazy danych przed każdym testem
        productDatabaseRepository.deleteAll();

        // Dodawanie przykładowego produktu
        ProductDatabase product = new ProductDatabase();
        product.setNameOfProduct("Kubek");
        product.setDescriptionOfProduct("Kubek z logo firmy");
        product.setPriceOfProduct("10");
        product.setDateOfAddition(LocalDateTime.now());
        product.setCountOfProduct(10);
        productDatabaseRepository.save(product);
    }

    @Test
    public void shouldReturnListOfProducts() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/products", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Kubek");
    }
}