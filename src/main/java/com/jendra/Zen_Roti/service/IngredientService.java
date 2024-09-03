package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.Ingredient;
import com.jendra.Zen_Roti.exception.IngredientNotFoundException;
import com.jendra.Zen_Roti.exception.OutOfStockException;

import java.util.List;
import java.util.Map;

public interface IngredientService {

    Ingredient save(Ingredient ingredient);

    List<Ingredient> save(List<Ingredient> ingredients);
    void reloadStockFromDb();

    boolean existsById(Long id);

    Ingredient findById(Long id) throws IngredientNotFoundException;

    void returnIngrToStockDb(Long recipeIngrId, Double recipeIngrQty, int proQty);
    void confirmStockAvailability(Long recipeIngrId, double recipeIngrQty, int proQty) throws IngredientNotFoundException;

    List<Ingredient> findAll();
    void deleteById(Long id) throws IngredientNotFoundException;

    Ingredient update(Ingredient ingredient) throws IngredientNotFoundException;

    // at payment time, we then use this method to reduce stock directly from db
    void useIngredient(Long recipeIngrId, Double recipeIngrQty, int proQty) throws IngredientNotFoundException, OutOfStockException;

    void saveIngrMinQty(Map<Long, Double> ingredientMinQuantities);
}
