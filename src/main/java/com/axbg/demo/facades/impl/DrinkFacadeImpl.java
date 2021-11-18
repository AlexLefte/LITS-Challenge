package com.axbg.demo.facades.impl;

import com.axbg.demo.dto.Drink;
import com.axbg.demo.dto.Ingredient;
import com.axbg.demo.dto.Recipe;
import com.axbg.demo.facades.DrinkFacade;
import com.axbg.demo.services.DrinkService;
import com.axbg.demo.services.RecipeService;
import com.axbg.demo.services.RecognitionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DrinkFacadeImpl implements DrinkFacade {
    private static final String DELIMITER = "&";

    private final RecognitionService recognitionService;
    private final DrinkService drinkService;
    private final RecipeService recipeService;

    public DrinkFacadeImpl(RecognitionService recognitionService, DrinkService drinkService, RecipeService recipeService) {
        this.recognitionService = recognitionService;
        this.drinkService = drinkService;
        this.recipeService = recipeService;
    }

    @Override
    public Map<String, List<Drink>> getDrinksByIngredients(List<Ingredient> ingredients) {
        Map<String, List<Drink>> ingredientWithDrinks = ingredients.stream()
                .collect(Collectors.toMap(Ingredient::getName, this.drinkService::getDrinksByIngredient));

        Set<Drink> uniqueDrinks = ingredientWithDrinks.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());

        Map<String, List<Drink>> result = new TreeMap<>((o1, o2) -> {
            int elementsComparisonResult = Integer.compare(o2.split(DELIMITER).length, o1.split(DELIMITER).length);
            return elementsComparisonResult != 0 ? elementsComparisonResult : o1.compareTo(o2);
        });

        for (Drink drink : uniqueDrinks) {
            List<String> composedKey = new ArrayList<>();

            for (Map.Entry<String, List<Drink>> entry : ingredientWithDrinks.entrySet()) {
                if (entry.getValue().contains(drink)) {
                    composedKey.add(entry.getKey());
                }
            }

            String newKey = String.join(DELIMITER, composedKey);
            if (!result.containsKey(newKey)) {
                result.put(newKey, new ArrayList<>());
            }

            result.get(newKey).add(drink);
        }

        return result;
    }

    @Override
    public Map<String, List<Drink>> getDrinksByPictures(List<MultipartFile> pictures) {
        List<Ingredient> ingredients = pictures.parallelStream().map(this.recognitionService::recognizeIngredient)
                .filter(Objects::nonNull)
                .map(ingredient -> URLEncoder.encode(ingredient.toLowerCase(), StandardCharsets.UTF_8))
                .map(Ingredient::new)
                .collect(Collectors.toList());

        return this.getDrinksByIngredients(ingredients);
    }

    @Override
    public Recipe getRecipeByDrinkId(int id) {
        return this.recipeService.getRecipeByDrinkId(id);
    }
}
