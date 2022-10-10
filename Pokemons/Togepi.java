package Pokemons;

import attacks.PhysicalAttacks.Facade;
import attacks.SatusAttacks.Confide;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Togepi extends Pokemon {
    public Togepi(String name, int level) {
        super (name, level);
        setStats(35, 20, 65, 40, 65, 20);
        setType(Type.FAIRY);
        setMove(new Facade(), new Confide());
    }
}