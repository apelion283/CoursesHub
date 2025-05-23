package com.courseshubbackend.formatters;

import com.courseshubbackend.pojos.Category;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CategoryFormatter implements Formatter<Category> {
    @Override
    public Category parse(String categoryId, Locale locale) throws ParseException {
        Category category = new Category();
        category.setId(Integer.parseInt(categoryId));
        return category;
    }

    @Override
    public String print(Category category, Locale locale) {
        return String.valueOf(category.getId());
    }
}
