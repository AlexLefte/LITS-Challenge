package com.axbg.demo.facades;

import com.axbg.demo.dto.Drink;
import com.axbg.demo.dto.Ingredient;
import com.axbg.demo.dto.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DrinkFacade {
    Map<String, List<Drink>> getDrinksByIngredients(List<Ingredient> ingredients);

    Map<String, List<Drink>> getDrinksByPictures(List<MultipartFile> pictures);

    Recipe getRecipeByDrinkId(int id);
}
