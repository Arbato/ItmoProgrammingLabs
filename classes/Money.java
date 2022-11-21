package classes;

public class Money {
    private int dollars;
    private int dimes;
    private int pennies;
    public Money(int dollars, int dimes, int pennies){
        this.dimes = dimes;
        this.dollars = dollars;
        this.pennies = pennies;
    }

    public int getDimes() {
        return dimes;
    }

    public int getPennies() {
        return pennies;
    }

    public int getDollars() {
        return dollars;
    }
    @Override
    public int hashCode(){
        var hash = this.dollars*100 + dimes*10 + pennies;
        return hash;
    }
    public boolean equals(Object m){
        if (m == this) {
            return true;
        }
        if (!(m instanceof MoonMoney)) {
            return false;
        }
cd
        return this.hashCode() == m.hashCode();
    }
}

