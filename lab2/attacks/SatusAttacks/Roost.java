package attacks.SatusAttacks;

import ru.ifmo.se.pokemon.*;

public class Roost extends StatusMove {
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