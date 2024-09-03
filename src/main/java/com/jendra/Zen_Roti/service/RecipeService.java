package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.Recipe;
import com.jendra.Zen_Roti.entity.RecipeDTO;
import com.jendra.Zen_Roti.exception.IngredientNotFoundException;
import com.jendra.Zen_Roti.exception.MissingIngredientException;
import com.jendra.Zen_Roti.exception.RecipeNotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

public interface RecipeService {
    Recipe save(RecipeDTO recipeDTO) throws MissingIngredientException, IngredientNotFoundException, SQLIntegrityConstraintViolationException;
    Recipe findById(Long id) throws RecipeNotFoundException;
    void deleteRecipeById(Long id) throws RecipeNotFoundException;
    Recipe update(RecipeDTO recipeDTO) throws RecipeNotFoundException, IngredientNotFoundException, SQLIntegrityConstraintViolationException, MissingIngredientException;

    Recipe update(RecipeDTO recipeDTO, Long id) throws RecipeNotFoundException, IngredientNotFoundException, SQLIntegrityConstraintViolationException, MissingIngredientException;

    List<Recipe> findAll();
    Recipe addRecipeIngredients(Map<Long, Double> recipeIngredient, Long recipeId) throws RecipeNotFoundException;

    boolean existsById(Long recipeId);
}
