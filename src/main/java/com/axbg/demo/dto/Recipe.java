package com.axbg.demo.dto;

import com.axbg.demo.deserializers.RecipeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@JsonDeserialize(using = RecipeDeserializer.class)
public class Recipe {
    private final String name;
    private final String picture;
    private final String glassType;
    private final String description;

    private final boolean alcoholic;

    private final List<String> tags;

    private final Map<String, String> ingredients;

    public Recipe(String name, String picture, String glassType, String description, boolean alcoholic,
                  List<String> tags, Map<String, String> ingredients) {
        this.name = name;
        this.picture = picture;
        this.glassType = glassType;
        this.description = description;
        this.alcoholic = alcoholic;
        this.tags = tags;
        this.ingredients = Collections.unmodifiableMap(ingredients);
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getGlassType() {
        return glassType;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAlcoholic() {
        return alcoholic;
    }

    public List<String> getTags() {
        return tags;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }
}
