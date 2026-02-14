package com.cookfromfridge.db;

import com.cookfromfridge.entities.Recipe;
import com.cookfromfridge.entities.RecipeIngredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
    private final DatabaseManager db;

    public RecipeRepository(DatabaseManager db) {
        this.db = db;
    }

    public List<Recipe> getAllRecipes() {
        String sql = "SELECT id, name, instructions FROM recipes ORDER BY name";
        List<Recipe> recipes = new ArrayList<>();

        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                recipes.add(new Recipe(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("instructions")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException("DB error: " + e.getMessage());
        }

        return recipes;
    }

    public List<RecipeIngredient> getRecipeIngredients(int recipeId) {
        String sql =
                "SELECT i.name, ri.amount " +
                        "FROM recipe_ingredients ri " +
                        "JOIN ingredients i ON i.id = ri.ingredient_id " +
                        "WHERE ri.recipe_id = ?";

        List<RecipeIngredient> items = new ArrayList<>();

        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, recipeId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(new RecipeIngredient(
                            rs.getString("name"),
                            rs.getDouble("amount")
                    ));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("DB error: " + e.getMessage());
        }

        return items;
    }
}