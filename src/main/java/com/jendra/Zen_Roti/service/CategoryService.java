package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.Category;
import com.jendra.Zen_Roti.exception.CategoryNotFoundException;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public interface CategoryService {
    Category save(Category category) throws ConstraintViolationException;
    List<Category> save(List<Category> categories)throws ConstraintViolationException;
    Category findById(Long id) throws CategoryNotFoundException;
    List<Category> findAll();
    void deleteById(Long id) throws CategoryNotFoundException;

    Category update(Category category) throws CategoryNotFoundException;
    boolean existsById(Long categoryId);
}
