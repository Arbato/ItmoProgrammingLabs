package classes;

import Characters.Person;

public class Dealer {
    public void dealFromEarthToMoon(Person person1, Money sum1,  Person person2, MoonMoney sum2){
        if (sum1.equals(sum2)){
            System.out.println("успешная сделка");
            person1.addMoney(-sum1.hashCode());
            person2.addMoney(sum2.hashCode());
        } else {
            System.out.println("не сошлись в цене");
        }
    }
    public void dealFromMoonToEarth(Person person2, MoonMoney sum2, Person person1, Money sum1){
        if (sum1.equals(sum2)){
            System.out.println("успешная сделка");
            person2.addMoney(-sum1.hashCode());
              person1.addMoney(sum2.hashCode());
        } else {
            System.out.println("не сошлись в цене");
        }
    }
}
