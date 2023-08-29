package attacks.PhysicalAttacks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class DragonRush extends PhysicalMove {
    public DragonRush() {
        super(Type.DRAGON, 100, 75);
    }
    @Override
    protected String describe(){
        return "attacks with DragonRush";
    }
}