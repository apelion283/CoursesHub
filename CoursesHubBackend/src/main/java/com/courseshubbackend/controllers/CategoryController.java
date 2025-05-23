package com.courseshubbackend.controllers;

import com.courseshubbackend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@ControllerAdvice
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute
    public void commonResponse(Model model){
        model.addAttribute("categories", this.categoryService.getCategories());
    }

    @GetMapping("/categories")
    public String categoriesView(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories());
        return "categories/categories";
    }
}
