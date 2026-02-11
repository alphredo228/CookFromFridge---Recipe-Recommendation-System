package com.cookfromfridge;

import com.cookfromfridge.ui.ConsoleMenu;
import com.cookfromfridge.entities.Ingredient;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Get ingredients from user
        List<Ingredient> ingredients = ConsoleMenu.getIngredientsFromUser();

        // Display the ingredients entered by the user
        System.out.println("Entered ingredients:");
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getName() + ": " + ingredient.getQuantity());
        }
    }
}