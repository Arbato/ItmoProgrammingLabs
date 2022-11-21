package classes;
import enumsandinterfaces.*;
import Characters.*;

public class FreeSpinnersSociety implements Thing, Place {
    private String name;
    private String contents;
    private String members;
    public FreeSpinnersSociety(String name){
        this.name = name;
    }
    public void addMember(Person newMember) {
        if (members == null){
            members = "Люди в "+ this.name + ": "+ newMember.getName();
        } else {
            members = members + " " + newMember.getName();
        }
        System.out.println(members);
    }
    public void where( Location location, Thing thing) {
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
}
