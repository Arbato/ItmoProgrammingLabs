package Pokemons;

import attacks.PhysicalAttacks.DragonRush;
import attacks.PhysicalAttacks.Facade;
import attacks.SatusAttacks.Roost;
import attacks.SatusAttacks.Swagger;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Yveltal extends Pokemon {
    public Yveltal(String name, int level) {
        super (name, level);
        setStats(126,131,95,131,98,99);
        setType(Type.DARK, Type.FLYING);
        setMove(new Swagger(), new Roost(), new Facade(), new DragonRush());
    }
}