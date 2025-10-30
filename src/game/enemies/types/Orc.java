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

public class Orc extends Enemy {
    public Orc(String name) {
        super(
            name,
            new HealthValues(45, 5),
            new ManaValues(10, 5),
            new Attributes(15, 9,15, 13, 14, 10, 8),
            new Resistances(),
            new TargetingAbility[]{ AbilityDatabase.FLASH_BANG },
            3
        );

        this.addSpecies(new SpeciesType(SpeciesCategory.HUMANOID, "Orc"));
        setTypeKey(EnemyKey.ORC.key());
    }
}

