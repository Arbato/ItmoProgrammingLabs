import Characters.Person;
import classes.*;
import classes.foods.*;
import enumsandinterfaces.*;
import java.util.Set;


public class Main {
    public static void main(String[] args) throws Exception {
        Person znaika = new Person("знайка",0);
        Person ponchik = new Person("пончик",8);
        Person neznaika = new Person("незнайка",0);

        znaika.startSpeaking();
        Table table = new Table();

        Ingredient cheese = new Ingredient("pepperjack", 100, Set.of(Taste.UMAMI, Taste.HOT));

        System.out.println(cheese.toString());


        Ingredient oatmeal = new Ingredient("овсянка", 100, Set.of(Taste.UMAMI, Taste.HOT));
        Ingredient cabbage = new Ingredient("Капуста", 100, Set.of(Taste.SWEET, Taste.COOL));
        Ingredient carrots = new Ingredient("Морковка", 100, Set.of(Taste.SWEET, Taste.COOL));
        Ingredient beetroot = new Ingredient("Свекла", 100, Set.of(Taste.SWEET, Taste.COOL));

        Recipe borshRecipe = new Recipe(Set.of(
                new IngredientItem<Ingredient, Measurment>(cabbage, Measurment.CUP),
                new IngredientItem<Ingredient, Measurment>(carrots, Measurment.CUP),
                new IngredientItem<Ingredient, Measurment>(beetroot, Measurment.CUP)));

        Dish borshik = new Dish("борщь", Set.of(
                new IngredientItem<Ingredient, Measurment>(cabbage, Measurment.CUP),
                new IngredientItem<Ingredient, Measurment>(carrots, Measurment.CUP),
                new IngredientItem<Ingredient, Measurment>(beetroot, Measurment.CUP)),
                borshRecipe);

        table.setName("стол");
        ponchik.where(Location.BEHIND, table);
        Foodstufs kasha = new Foodstufs("каша", 4);
        Foodstufs borsh = new Foodstufs("борьщ", 4);
        Plate plate1 = new Plate("plate 1");
        Plate plate2 = new Plate("plate 2");
        oatmeal.where(Location.IN, plate1);
        borsh.where(Location.IN, plate2);

        ponchik.eat(kasha);
        ponchik.eat(borsh);
        ponchik.changeState(State.KALM);
        znaika.stopSpeaking();

        Rocket rocket = new Rocket("ракета");
        Moon moon = new Moon("луна");
        ponchik.startSpeaking();
        Foodstufs snacks = new Foodstufs("запасы", 20);
        ponchik.goTo(rocket);
        ponchik.where(Location.IN, rocket);
        neznaika.goTo(rocket);
        neznaika.where(Location.IN,rocket);
        rocket.goTo(moon);
        rocket.where(Location.ON, moon);
        neznaika.where(Location.ON, moon);
        ponchik.where(Location.ON, moon);
        Moon undermoon = new Moon("Подлунный мир");
        neznaika.goTo(undermoon);
        neznaika.where(Location.IN, undermoon);
        ponchik.eat(snacks);
        ponchik.timeWOfood(10);
        ponchik.eat(snacks);
        ponchik.goTo(undermoon);

        Person aliens = new Person("лунатики", 0);
        Money sum = new Money(0,1,4);
        MoonMoney sum1 = new MoonMoney(0,7);
        Dealer d = new Dealer();
        System.out.println(ponchik.getMoney());

        d.dealFromMoonToEarth( aliens, sum1, ponchik, sum);
        d.dealFromMoonToEarth( aliens, sum1, ponchik, sum);

        System.out.println(ponchik.getMoney());

        Money sum2 = new Money(0,2,9);
        MoonMoney sum3 = new MoonMoney(1,7);
        d.dealFromEarthToMoon(ponchik, sum2, aliens, sum3);

        System.out.println(ponchik.getMoney());
        LosPaganos losPaganos = new LosPaganos("ЛосПаганос");
        ponchik.where(Location.IN, losPaganos);

        Ferriswheel devil = new Ferriswheel();
        devil.setName("Чертого колесо");
        ponchik.workAt(devil);
        Person capitalistPig = new Person("cвин", 0);
        FreeSpinnersSociety fsp = new FreeSpinnersSociety("OCK", capitalistPig);
        fsp.addMember(ponchik);

    }
}
