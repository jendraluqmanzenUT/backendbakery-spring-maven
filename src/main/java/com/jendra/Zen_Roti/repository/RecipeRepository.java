package com.jendra.Zen_Roti.repository;

import com.jendra.Zen_Roti.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {

}
