package com.axbg.demo.services.impl;

import com.axbg.demo.dto.Drink;
import com.axbg.demo.dto.Ingredient;
import com.axbg.demo.dto.collections.DrinksCollection;
import com.axbg.demo.services.DrinkService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {
    private static final String FILTER_PATH = "/filter.php?i=";

    private final RestTemplate restTemplate;

    public DrinkServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Drink> getDrinksByIngredient(Ingredient ingredient) {
        DrinksCollection drinksList = this.restTemplate.getForObject(BASE_URL + FILTER_PATH + ingredient.getName(), DrinksCollection.class);
        return drinksList != null ? drinksList.getDrinks() : Collections.emptyList();
    }
}
