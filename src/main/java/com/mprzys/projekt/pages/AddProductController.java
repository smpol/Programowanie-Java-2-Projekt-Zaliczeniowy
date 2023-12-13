package com.mprzys.projekt.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddProductController {
    @RequestMapping("/addProduct")
    public String getMainPage() {
        return "addProduct";
    }
}
