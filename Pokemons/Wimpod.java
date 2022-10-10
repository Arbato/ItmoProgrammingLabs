package Pokemons;

import attacks.PhysicalAttacks.Facade;
import attacks.SatusAttacks.Confide;
import attacks.SpecialAttacks.StruggleBug;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Wimpod extends Pokemon {
    public Wimpod(String name, int level) {
        super(name, level);
        setStats(25,35, 40, 20, 30, 80);
        setType(Type.BUG, Type.WATER);
        setMove(new Confide(), new Facade(), new StruggleBug());
    }
}
