package com.courseshubbackend.repositories;

import com.courseshubbackend.pojos.Category;

import java.util.List;
import java.util.Map;

public interface CategoryRepository {
    List<Category> getCategories(Map<String, String> params);
}