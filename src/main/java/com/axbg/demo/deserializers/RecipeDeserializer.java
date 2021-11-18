package com.axbg.demo.deserializers;

import com.axbg.demo.dto.Recipe;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RecipeDeserializer extends JsonDeserializer<Recipe> {
    private static final String INGREDIENT_TAG = "strIngredient";
    private static final String MEASURE_TAG = "strMeasure";

    @Override
    public Recipe deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode root = jp.readValueAsTree();
        ArrayNode drinks = (ArrayNode) root.get("drinks");
        JsonNode drink = drinks.get(0);

        return new Recipe(drink.get("strDrink").asText(),
                drink.get("strDrinkThumb").asText(),
                drink.get("strGlass").asText(),
                drink.get("strInstructions").asText(),
                drink.get("strAlcoholic").asText().equals("Alcoholic"),
                Arrays.stream(drink.get("strTags").asText().split(",")).collect(Collectors.toList()),
                parseIngredients(drink));
    }

    private Map<String, String> parseIngredients(JsonNode node) {
        Map<String, String> ingredients = new HashMap<>();

        for (int i = 1; i < 16; i++) {
            String ingredient = node.get(INGREDIENT_TAG + i).asText();

            if ("null".equals(ingredient)) {
                break;
            }

            String measure = node.get(MEASURE_TAG + i).asText();
            ingredients.put(ingredient, measure);
        }

        return ingredients;
    }
}
