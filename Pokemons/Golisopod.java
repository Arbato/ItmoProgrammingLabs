package Pokemons;

import attacks.PhysicalAttacks.Slash;
import ru.ifmo.se.pokemon.Type;

public class Golisopod extends Wimpod {
    public Golisopod(String name, int level) {
        super(name, level);
        setType(Type.BUG, Type.WATER);
        setStats(75, 125, 140, 60, 90, 40);
        addMove(new Slash());
    }
}
