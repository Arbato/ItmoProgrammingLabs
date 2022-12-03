package classes.foods;
import enumsandinterfaces.Measurment;
import java.util.Set;

public class Recipe {
    private Set<IngredientItem<Ingredient, Measurment>> theRecepie;
    private String dishName;

    public Recipe(Set<IngredientItem<Ingredient, Measurment>> recipe){
        theRecepie = recipe;
    }

    public Set<IngredientItem<Ingredient, Measurment>> getTheRecipe() {
        return theRecepie;
    }
}
