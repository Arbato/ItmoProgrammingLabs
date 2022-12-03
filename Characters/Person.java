package Characters;
import classes.foods.Foodstufs;
import classes.foods.Ingredient;
import enumsandinterfaces.Location;
import enumsandinterfaces.State;
import enumsandinterfaces.Thing;

public class Person implements Thing {
    private String name;
    private State state;
    private int money;
    private int hungerScale;
    private String whereAmI;

    public Person(String name, int hungerScale){
        this.name = name;
        this.hungerScale = hungerScale;
    }

    @Override
    public String toString() {
        return state + name + " c количеством денег: " + money + " и уровнем голода: " + hungerScale;
    }

    public void workAt(Thing thing){
        System.out.println(this.name+ " работает на " + thing.getName());
    }
    public void addMoney(int s){ //todo replace void with smth
        money += s;
    }
    public void goTo(Thing thing){
        whereAmI = "";
    }

    public void trade(Person p, int price) {
        this.money = this.money + price;
        p.money = p.money - price;
    }
    public int getMoney(){
        return money;
    }

    public void eat(Foodstufs food){
        if (hungerScale <= food.getAmmount()){

            food.setAmmount(food.getAmmount() - hungerScale);
            hungerScale = 0;

            System.out.println(this.name+ " наелся, возможно еда даже осталась"); //todo remove from method print call
        } else if (hungerScale > food.getAmmount()){
            hungerScale = hungerScale - food.getAmmount();
            food.setAmmount(0);

            System.out.println(this.name+ " не наелся");
        }
    }

    public boolean equals(Person obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Person other = (Person) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }

        return true;
    }



    public void startSpeaking (){
        System.out.println(this.name + " сказал <<");
    }
    public void stopSpeaking(){
        System.out.print(">>\n");
    }

    public void where(Location location, Thing thing) {
        if (whereAmI == null){
            whereAmI = this.name+" "+ location.getName() +" " + thing.getName() ;
        } else {
            whereAmI = whereAmI +" " +  this.name+" "+ location.getName() +" " + thing.getName() ;;
        }
        System.out.println(whereAmI);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.name.hashCode();
        return hash;
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
