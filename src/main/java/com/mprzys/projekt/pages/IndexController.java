package com.mprzys.projekt.pages;

import com.mprzys.projekt.database.ProductDatabase;
import com.mprzys.projekt.services.ProductDatabaseService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
public class IndexController {




    @RequestMapping("/")
    public String getMainPage() {
        return "index";
    }

    //Load defaults for testing

    //dodanie przykładowych produktów do bazy danych przy starcie aplikacji


}
