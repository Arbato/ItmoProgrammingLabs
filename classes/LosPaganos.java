package classes;
import enumsandinterfaces.Location;
import enumsandinterfaces.Place;
import enumsandinterfaces.Thing;

public class LosPaganos implements Thing, Place {

    String name;
    String contents;
    public LosPaganos(String givenName){
        name = givenName;
    }

    public void setName(String newname) {
        name = newname;
    }

    public String getName(){
        return name;
    }

    public void where(Location location, Thing thing) {
        contents = contents + thing.getName() +" "+ location.getName() + " " +this.name;
        System.out.println(contents);
    }
}
