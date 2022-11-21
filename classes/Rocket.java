package classes;

import enumsandinterfaces.Location;
import enumsandinterfaces.Place;
import enumsandinterfaces.Thing;

public class Rocket implements Place, Thing {
    private String name;
    private String contents;
    private String baggage;
    private String ownlocation;

    public Rocket(String name){
        this.name = name;
    }
    public void putIn(Thing thing){
        if (baggage == null){
            baggage = this.name+" " + thing.getName();
        } else {
            baggage = baggage  +" " + thing.getName();
        }
        System.out.println(contents);
    }

    public void goTo(Thing thing){
        ownlocation = thing.getName();
        contents = "";
    }

    public void where(Location location, Thing thing) {
        if (contents == null){
            contents = this.name+" "+ location.getName() +" " + thing.getName() ;
        } else {
            contents = contents +  this.name+" "+ location.getName() +" " + thing.getName() ;;
        }
        System.out.println(contents);
    }

    public void setName(String newname) {
        name = newname;
    }

    public String getName() {
        return name;
    }
    public String getLocation(){
        return ownlocation;
    }
}
