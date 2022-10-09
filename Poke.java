import attacks.SpecialAttacks.SweetKiss;
import ru.ifmo.se.pokemon.*;
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


// attack classes
class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL, 70, 100);
    }
    @Override
    protected String describe(){
        return "attacks with Facade";
    }
}

class Confide extends StatusMove {
    public Confide() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected String describe(){
        return "attacks with Confide";
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        p.setMod(Stat.SPECIAL_ATTACK, -1);
        super.applyOppEffects(p);
    }

}

class Roost extends StatusMove {
    public Roost() { super(Type.FLYING, 0, 0);}

    @Override
    protected String describe(){
        return "attacks with Roost";
    }
    @Override
    protected void applySelfEffects(Pokemon p) {
        double half = p.getHP()/2;
        if (half > p.getHP()) {
            Effect e = new Effect().stat(Stat.HP, (int) Math.round(half));
            p.addEffect(e);
        }

        super.applySelfEffects(p);
    }
}


class DragonRush extends PhysicalMove {
    public DragonRush() {
        super(Type.DRAGON, 100, 75);
    }
    @Override
    protected String describe(){
        return "attacks with DragonRush";
    }
}


class Swagger extends StatusMove{
    public Swagger() {
        super(Type.NORMAL, 0,85);
    }
    @Override
    protected String describe(){
        return "attacks with Swagger";
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect e = new Effect().turns(-1).stat(Stat.ATTACK, 2);
        p.addEffect(e);
        Effect.confuse(p);
        super.applyOppEffects(p);
    }
}

class StruggleBug extends SpecialMove {
    public StruggleBug(){
        super(Type.BUG, 50, 100);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect e = new Effect().turns(-1).stat(Stat.SPECIAL_ATTACK, -1);
        p.addEffect(e);
        super.applyOppEffects(p);
    }
    @Override
    protected String describe(){
        return "attacks with Struggle Bug";
    }
}

class Slash extends PhysicalMove {
    public Slash() {
        super(Type.NORMAL, 70,100);
    }
    @Override
    protected String describe() {
        return "attacks with Slash";
    }
}


// pokemon classes
class Yveltal extends Pokemon {
    public Yveltal(String name, int level) {
        super (name, level);
        setStats(126,131,95,131,98,99);
        setType(Type.DARK, Type.FLYING);
        setMove(new Swagger(), new Roost(), new Facade(), new DragonRush());
    }
}

class Wimpod extends Pokemon {
    public Wimpod(String name, int level) {
        super(name, level);
        setStats(25,35, 40, 20, 30, 80);
        setType(Type.BUG, Type.WATER);
        setMove(new Confide(), new Facade(), new StruggleBug());
    }
}

class Golisopod extends Wimpod {
    public Golisopod(String name, int level) {
        super(name, level);
        setType(Type.BUG, Type.WATER);
        setStats(75, 125, 140, 60, 90, 40);
        addMove(new Slash());
    }
}


class Togepi extends Pokemon {
    public Togepi(String name, int level) {
        super (name, level);
        setStats(35, 20, 65, 40, 65, 20);
        setType(Type.FAIRY);
        setMove(new Facade(), new Confide());
    }
}

class Togetic extends Togepi {
    public Togetic(String name, int level) {
        super(name, level);
        setStats(55, 40, 85, 80, 105, 40);
        setType(Type.FAIRY, Type.FLYING);
        addMove(new SweetKiss());
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
