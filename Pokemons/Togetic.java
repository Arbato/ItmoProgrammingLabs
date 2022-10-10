package Pokemons;

import attacks.SpecialAttacks.SweetKiss;
import ru.ifmo.se.pokemon.Type;

public class Togetic extends Togepi {
    public Togetic(String name, int level) {
        super(name, level);
        setStats(55, 40, 85, 80, 105, 40);
        setType(Type.FAIRY, Type.FLYING);
        addMove(new SweetKiss());
    }
}
