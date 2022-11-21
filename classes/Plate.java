package classes;

import enumsandinterfaces.Location;
import enumsandinterfaces.Place;
import enumsandinterfaces.Thing;

public class Plate implements Place, Thing {
    private String name;
    private String contents;
    public Plate(String name){
        this.name=name;
    }
    public void where(Location location, Thing thing) {
        if (contents == null){
            contents = this.name+" "+ location.getName() +" " + thing.getName() ;
        } else {
            contents = contents +" "+  this.name+" "+ location.getName() +" " + thing.getName();
        }
        System.out.println(contents);
    }

    public void setName(String newname) {
        name = newname;
    }

    public String getName() {
        return name;
    }
}
