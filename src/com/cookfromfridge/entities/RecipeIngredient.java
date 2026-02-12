package com.cookfromfridge.entities;

public class RecipeIngredient {
    private String recipeName;
    private String ingredientName;

    public RecipeIngredient(String recipeName, String ingredientName) {
        this.recipeName = recipeName;
        this.ingredientName = ingredientName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getIngredientName() {
        return ingredientName;
    }
}