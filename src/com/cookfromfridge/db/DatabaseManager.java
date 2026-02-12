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
