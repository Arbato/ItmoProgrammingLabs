import attacks.PhysicalAttacks.*;
import attacks.SpecialAttacks.*;
import attacks.SatusAttacks.*;
import ru.ifmo.se.pokemon.*;
import Pokemons.*;
import java.lang.*;

public class Poke {
    public static void main(String args[]) {
        Battle b = new Battle();
        Pokemon p1 = new Togepi("стептер", 1);
        Pokemon p2 = new Togetic("бас", 1);
        Pokemon p3 = new Togekiss("тридэус", 1);
        Pokemon p4 = new Yveltal("бобби сбобобствен", 1);
        Pokemon p5 = new Wimpod("оливьер", 1);
        Pokemon p6 = new Golisopod("зубака", 1);

        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }
}



class Togekiss extends Togetic {
    public Togekiss(String name, int level) {
        super(name, level);
        setStats(85, 50, 95, 120, 115, 80);
        setType(Type.FAIRY, Type.FLYING);
        addMove(new Roost());
    }
}
