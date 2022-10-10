package attacks.SatusAttacks;

import ru.ifmo.se.pokemon.*;

public class Swagger extends StatusMove {
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