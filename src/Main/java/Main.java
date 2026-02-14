import com.cookfromfridge.db.DatabaseManager;
import com.cookfromfridge.db.RecipeRepository;
import com.cookfromfridge.entities.Ingredient;
import com.cookfromfridge.entities.Recipe;
import com.cookfromfridge.service.RecipeService;
import com.cookfromfridge.ui.ConsoleMenu;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5433/cookfromfridge";
        String user = "postgres";
        String password = "oop228";

        DatabaseManager db = DatabaseManager.getInstance(url, user, password);
        RecipeRepository repo = new RecipeRepository(db);
        RecipeService service = new RecipeService(repo);

        List<Ingredient> userIngredients = ConsoleMenu.getIngredientsFromUser();
        List<Recipe> matches = service.findMatchingRecipes(userIngredients);

        if (matches.isEmpty()) {
            System.out.println("No recipes found for your ingredients.");
            return;
        }

        System.out.println("\nMatching recipes:");
        for (Recipe r : matches) {
            System.out.println("\n=== " + r.getName() + " ===");
            System.out.println(r.getInstructions());
        }
    }
}