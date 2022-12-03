package classes.foods;

public class IngredientItem<Ingredient, Measurement> {

    private final Ingredient ingredient;
    private final Measurement measurement;

    public IngredientItem(Ingredient x, Measurement y) {
        this.ingredient= x;
        this.measurement = y;
    }

    @Override
    public String toString() {
        return "IngredientPair{" +
                "ingredient=" + ingredient +
                ", measurement=" + measurement +
                '}';
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {

            System.out.println(this);
            return false;
        }
        if (obj.getClass() != this.getClass()) {

            System.out.println(this);
            return false;
        }
        final IngredientItem other = (IngredientItem) obj;

        if (this.ingredient != other.ingredient) {
            System.out.println(this);

            return false;

        }

        if (this.measurement != other.measurement) {

            System.out.println(this);
            return false;

        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.ingredient.hashCode();
        hash = 53 * hash + this.measurement.hashCode();
        return hash;
    }

    public Measurement getMeasurement() {
        return measurement;
    }
}