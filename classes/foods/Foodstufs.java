package classes.foods;

import enumsandinterfaces.Location;
import enumsandinterfaces.Thing;

public class Foodstufs implements Thing{
    private String name;
    public int ammount;
    private String ingredients;

    public Foodstufs(String name, int a){
        this.name=name;
        ammount = a;
    }

    public void where(Location location, Thing thing) {
        if (ingredients == null){
            ingredients = this.name+" "+ location.getName() +" " + thing.getName() ;
        } else {
            ingredients = ingredients +" " +  this.name+" "+ location.getName() +" " + thing.getName() ;;
        }
        System.out.println(ingredients);
    }

    public void setName(String newname) {
        name = newname;
    }

    public String getName() {
        return name;
    }
}


