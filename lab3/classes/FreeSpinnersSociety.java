package classes;
import enumsandinterfaces.*;
import Characters.*;

import java.util.*;

import java.util.Collections;
import java.util.Set;

public class FreeSpinnersSociety implements Thing, Place {
    private String name;
    private String contents;
    private String memberNames;
    private Set<Person> members;
    public FreeSpinnersSociety(String name, Person firstMember){
        this.name = name;
        this.members = new HashSet<Person>();
        this.members.add(firstMember);
        memberNames = firstMember.getName();
    }

    @Override
    public String toString(){
        return name+ ", люди в этом обществе: " + memberNames;
    }
    public void addMember(Person newMember) throws Exception {

        members.add(newMember);
        try {
            members.add(newMember);

            if (memberNames == null){
                memberNames = "Люди в "+ this.name + ": "+ newMember.getName();
            } else {
                memberNames = memberNames + " " + newMember.getName();
            }
        } catch (Exception e) {
            throw new Exception("something wrong with adding a member");
        }

    }
    public void where( Location location, Thing thing) {
        if (contents == null){
            contents = this.name+" "+ location.getName() +" " + thing.getName() ;
        } else {
            contents = contents +  this.name+" "+ location.getName() + " " + thing.getName() ;;
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
