
package attacks.SpecialAttacks;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class SweetKiss extends StatusMove {
    public SweetKiss() {
        super(Type.FAIRY, 0, 75);
    }
    @Override
    protected String describe(){
        return "attacks with SweetKiss";
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        p.confuse();
        super.applyOppEffects(p);
    }
}