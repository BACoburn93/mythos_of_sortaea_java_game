package enemies.types;

import abilities.ability_types.TargetingAbility;
import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import actors.species.*;
import enemies.Enemy;
import enemies.EnemyKey;

public class Goblin extends Enemy {
    public Goblin(String name) {
        super(
            name,
            new HealthValues(30, 3),
            new ManaValues(10, 3),
            new Attributes(10, 12, 8, 10, 8, 8, 8),
            new Resistances(),
            new TargetingAbility[]{ AbilityDatabase.FLASH_BANG },
            1
        );

        this.addSpecies(new SpeciesType(SpeciesCategory.HUMANOID, "Goblin"));
        setTypeKey(EnemyKey.GOBLIN.key());
    }

}
