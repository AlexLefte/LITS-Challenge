package com.axbg.demo.services;

import com.axbg.demo.dto.Drink;
import com.axbg.demo.dto.Ingredient;

import java.util.List;

public interface DrinkService extends GenericService {
    List<Drink> getDrinksByIngredient(Ingredient ingredient);
}
