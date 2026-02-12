package com.cookfromfridge;

import com.cookfromfridge.ui.ConsoleMenu;
import com.cookfromfridge.entities.Ingredient;
import com.cookfromfridge.db.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Ingredient> ingredients = ConsoleMenu.getIngredientsFromUser();

        System.out.println("Entered ingredients:");
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getName() + ": " + ingredient.getQuantity());

            // Получаем рецепты на основе ингредиента
            ResultSet recipes = DatabaseManager.getRecipesByIngredient(ingredient.getName());
            try {
                if (recipes != null) {
                    while (recipes.next()) {
                        String recipeName = recipes.getString("name");
                        String instructions = recipes.getString("instructions");
                        System.out.println("Recipe: " + recipeName + "\nInstructions: " + instructions);
                    }
                } else {
                    System.out.println("No recipes found for " + ingredient.getName());
                }
            } catch (SQLException e) {
                System.out.println("Error while fetching recipes: " + e.getMessage());
            }
        }
    }
}