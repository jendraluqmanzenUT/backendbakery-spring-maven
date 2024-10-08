package com.jendra.Zen_Roti.entity;

import com.jendra.Zen_Roti.exception.CategoryNotFoundException;
import com.jendra.Zen_Roti.exception.RecipeNotFoundException;
import com.jendra.Zen_Roti.service.CategoryService;
import com.jendra.Zen_Roti.service.RecipeService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ProductDTO {

    private String name;

    private Long recipeId;

    private Long categoryId;

    private String image;

    private double price;

    private double weight;

    private Unit unit;

    private String nutrientInfo;

    private String description;

    private String warnings;

    public Product toProduct(CategoryService categoryService, RecipeService recipeService)throws RecipeNotFoundException, CategoryNotFoundException {

        Product product = new Product();
        product.setName(this.name);
        product.setRecipe(recipeService.findById(this.recipeId));
        product.setCategory(categoryService.findById(this.categoryId));
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setWarnings(this.warnings);
        product.setNutrientInfo(this.nutrientInfo);
        product.setImage(this.image);
        return product;
    }


}
