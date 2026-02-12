package com.cookfromfridge.db;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/cookfromfridge";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void addIngredient(String name, int quantity) {
        String sql = "INSERT INTO ingredients (name, quantity) VALUES (?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setInt(2, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addRecipe(String name, String instructions) {
        String sql = "INSERT INTO recipes (name, instructions) VALUES (?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, instructions);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Новый метод для получения рецептов по ингредиенту
    public static ResultSet getRecipesByIngredient(String ingredient) {
        String sql = "SELECT r.name, r.instructions FROM recipes r " +
                     "JOIN recipe_ingredients ri ON r.id = ri.recipe_id " +
                     "JOIN ingredients i ON ri.ingredient_id = i.id " +
                     "WHERE i.name = ?";
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ingredient);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
