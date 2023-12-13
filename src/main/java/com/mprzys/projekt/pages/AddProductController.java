package com.mprzys.projekt.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddProductController {
    @RequestMapping("/addproduct")
    public String getMainPage() {
        return "addProduct";
    }
}
