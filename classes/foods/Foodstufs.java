package classes.foods;
import enumsandinterfaces.Location;
import enumsandinterfaces.Thing;
import java.util.List;
import java.util.Set;

import enumsandinterfaces.Taste;

public class Foodstufs implements Thing{
    private String name;



    private int ammount; //change to private DONE
    private String situation;

    public Foodstufs(String name, int a){
        this.name=name;
        ammount = a;
    }

    @Override
    public String toString(){
        return situation +" of ammount: " + ammount;

    }

    public void where(Location location, Thing thing) {
        if (situation == null){
            situation = this.name+" "+ location.getName() +" " + thing.getName() ;
        } else {
            situation = situation +" " +  this.name+" "+ location.getName() +" " + thing.getName() ;;
        }
    }
    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }
    public void setName(String newname) {
        name = newname;
    }

    public String getName() {
        return name;
    }
}


