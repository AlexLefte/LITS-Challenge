package com.axbg.demo.services.impl;

import com.axbg.demo.dto.Recipe;
import com.axbg.demo.services.RecipeService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static final String LOOKUP_PATH = "/lookup.php?i=";

    private final RestTemplate restTemplate;

    public RecipeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Recipe getRecipeByDrinkId(int id) {
        return this.restTemplate.getForObject(BASE_URL + LOOKUP_PATH + id, Recipe.class);
    }
}
