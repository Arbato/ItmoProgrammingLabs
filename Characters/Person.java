package Characters;
import classes.foods.Foodstufs;
import enumsandinterfaces.Location;
import enumsandinterfaces.State;
import enumsandinterfaces.Thing;

public class Person implements Thing {
    private String name;
    private State state;
    private int money;
    private int hungerScale;
    private String contents;

    public Person(String name, int hungerScale){
        this.name = name;
        this.hungerScale = hungerScale;
    }
    public void workAt(Thing thing){
        System.out.println(this.name+ " работает на " + thing.getName());
    }
    public void addMoney(int s){
        money += s;
    }
    public void goTo(Thing thing){
        contents = "";
    }

    public void trade(Person p, int price) {
        this.money = this.money + price;
        p.money = p.money - price;
    }
    public int getMoney(){
        return money;
    }

    public void eat(Foodstufs food){
        if (hungerScale <= food.ammount){
            food.ammount = food.ammount - hungerScale;
            hungerScale = 0;


            System.out.println(this.name+ " наелся, возможно еда даже осталась");

        } else if (hungerScale > food.ammount){
            hungerScale = hungerScale - food.ammount;
            food.ammount = 0;

            System.out.println(this.name+ " не наелся");
        }
    }

    public void startSpeaking (){
        System.out.println(this.name + " сказал <<");
    }
    public void stopSpeaking(){
        System.out.print(">>\n");
    }

    public void where(Location location, Thing thing) {
        if (contents == null){
            contents = this.name+" "+ location.getName() +" " + thing.getName() ;
        } else {
            contents = contents +" " +  this.name+" "+ location.getName() +" " + thing.getName() ;;
        }
        System.out.println(contents);
    }


    public void changeState(State state) {
        this.state = state;
        if (state == State.KALM) {
            System.out.println(this.name + " успокоился");
        }
        if (state == State.PANIK) {
            System.out.println(this.name + " заволновался");
        }
    }

    public void timeWOfood(int x){
        hungerScale = hungerScale + x*2;
    }

    public void setName(String newname) {
        name = newname;
    }

    public String getName() {
        return name;
    }

}
