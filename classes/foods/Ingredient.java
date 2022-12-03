package classes.foods;
import enumsandinterfaces.Location;
import enumsandinterfaces.Taste;
import enumsandinterfaces.Thing;

import java.util.Set;

public class Ingredient implements Thing {
    private String name;
    private String situation;
    private Integer calories;
    private Set<Taste> tastes;

    public Ingredient (String n, Integer cal, Set<Taste> t){
        name=n;
        calories = cal;
        tastes = t;
    }
    @Override
    public String toString(){
        return name + calories + tastes + situation;
    }

    @Override
    public void setName(String newname) {
        name = newname;
    }
    public void where(Location location, Thing thing) {
        if (situation == null){
            situation = this.name+" "+ location.getName() +" " + thing.getName() ;
        } else {
            situation = situation + " " + this.name + " " + location.getName() + " " + thing.getName();

        }
    }

    public String getName() {
        return name;
    }

    public Set<Taste> getTastes() {
        return tastes;
    }

    public Integer getCalories() {
        return calories;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Ingredient other = (Ingredient) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }

        if (this.calories != other.calories) {
            return false;
        }

        if (this.tastes != other.tastes) {
            return false;
        }
        return true;
    }
}