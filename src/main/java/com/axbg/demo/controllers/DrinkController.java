package com.axbg.demo.controllers;

import com.axbg.demo.dto.Drink;
import com.axbg.demo.dto.Ingredient;
import com.axbg.demo.dto.Recipe;
import com.axbg.demo.facades.DrinkFacade;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/drink")
public class DrinkController {
    private final DrinkFacade drinkFacade;

    public DrinkController(DrinkFacade drinkFacade) {
        this.drinkFacade = drinkFacade;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, List<Drink>>> getDrinksFromIngredients(@RequestBody List<Ingredient> ingredients) {
        return ResponseEntity.ok(this.drinkFacade.getDrinksByIngredients(ingredients));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, List<Drink>>> getDrinksFromPictures(@RequestPart List<MultipartFile> pictures) {
        return ResponseEntity.ok(this.drinkFacade.getDrinksByPictures(pictures));
    }

    @GetMapping(value = "/{drinkId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Recipe> getRecipeByDrinkId(@PathVariable("drinkId") int drinkId) {
        return ResponseEntity.ok(this.drinkFacade.getRecipeByDrinkId(drinkId));
    }
}
