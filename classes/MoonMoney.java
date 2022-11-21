package classes;

import java.util.PrimitiveIterator;

public class MoonMoney {
    private int goggles;
    private int mumbles;
    public MoonMoney(int goggles, int mumbles){
        this.goggles = goggles;
        this.mumbles = mumbles;
    }
    public int getGoggles(){
        return goggles;
    }
    public int getMumbles(){
        return mumbles;
    }
    @Override
    public int hashCode(){
        var hash = goggles * 15 + mumbles * 2;
        return hash;
    }
    @Override
    public boolean equals(Object m){
        if (m == this) {
            return true;
        }
        if (!(m instanceof Money)) {
            return false;
        }

        return this.hashCode() == m.hashCode();
    }
}
