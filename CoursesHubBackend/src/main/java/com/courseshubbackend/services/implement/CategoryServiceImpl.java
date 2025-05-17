package com.courseshubbackend.services.implement;

import com.courseshubbackend.pojos.Category;
import com.courseshubbackend.repositories.CategoryRepository;
import com.courseshubbackend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.getCategories(null);
    }
}
