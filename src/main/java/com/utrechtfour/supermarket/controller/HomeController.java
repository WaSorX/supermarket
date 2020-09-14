package com.utrechtfour.supermarket.controller;

import jdk.jfr.ContentType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getStyledPage(Model model) {
        model.addAttribute("name", "Supermarket API");
        return "supermarket.md";
    }


}
