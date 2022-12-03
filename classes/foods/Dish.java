package classes.foods;
import enumsandinterfaces.Measurment;

import java.util.Set;

public class Dish {
    private String name;
    private Set<Ingredient> ingredients;
    public Dish (String n, Set<IngredientItem<Ingredient, Measurment>> ing, Recipe r) throws Exception {
        if (r.getTheRecipe().equals(ing)){
            this.name = n;
        } else {
            throw new Exception("You have the wrong ingredients!!!!! You can't make "+ n + " like that!" );
        }

    }
    @Override
    public String toString(){
        return name+ " made from "+ ingredients.toString();
    }
}
