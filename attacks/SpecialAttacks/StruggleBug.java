package attacks.SpecialAttacks;
import ru.ifmo.se.pokemon.*;

public class StruggleBug extends SpecialMove {
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