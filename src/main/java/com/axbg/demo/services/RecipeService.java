package com.axbg.demo.services;

import com.axbg.demo.dto.Recipe;

public interface RecipeService extends GenericService {
    Recipe getRecipeByDrinkId(int id);
}
