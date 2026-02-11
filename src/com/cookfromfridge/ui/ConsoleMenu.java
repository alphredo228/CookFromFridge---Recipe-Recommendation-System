package com.cookfromfridge.ui;

import com.cookfromfridge.entities.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    public static List<Ingredient> getIngredientsFromUser() {
        Scanner scanner = new Scanner(System.in);
        List<Ingredient> ingredients = new ArrayList<>();

        System.out.println("Enter ingredients (name and quantity separated by space): ");
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Invalid format! Enter: ingredient_name quantity");
                continue;
            }

            String name = parts[0];
            int quantity;
            try {
                quantity = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Quantity must be a number!");
                continue;
            }

            ingredients.add(new Ingredient(name, quantity));
        }

        return ingredients;
    }
}