package com.courseshubbackend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    @GetMapping("/categories")
    public String categoriesView() {
        return "categories/categories";
    }
}
