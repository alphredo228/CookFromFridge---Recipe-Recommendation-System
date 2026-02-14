package com.cookfromfridge.service;

import com.cookfromfridge.db.RecipeRepository;
import com.cookfromfridge.entities.Ingredient;
import com.cookfromfridge.entities.Recipe;
import com.cookfromfridge.entities.RecipeIngredient;

import java.util.*;

public class RecipeService {
    private final RecipeRepository repo;

    public RecipeService(RecipeRepository repo) {
        this.repo = repo;
    }

    public List<Recipe> findMatchingRecipes(List<Ingredient> userIngredients) {
        Map<String, Double> have = new HashMap<>();
        for (Ingredient ing : userIngredients) {
            have.put(normalize(ing.getName()), ing.getQuantity());
        }

        List<Recipe> result = new ArrayList<>();
        for (Recipe r : repo.getAllRecipes()) {
            List<RecipeIngredient> req = repo.getRecipeIngredients(r.getId());
            if (matches(req, have)) {
                result.add(r);
            }
        }
        return result;
    }

    private boolean matches(List<RecipeIngredient> req, Map<String, Double> have) {
        for (RecipeIngredient ri : req) {
            String key = normalize(ri.getIngredientName());
            Double userQty = have.get(key);
            if (userQty == null) return false;
            if (userQty + 1e-9 < ri.getRequiredAmount()) return false;
        }
        return true;
    }

    private String normalize(String s) {
        return s == null ? "" : s.trim().toLowerCase(Locale.ROOT);
    }
}